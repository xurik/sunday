package com.alibaba.china.jweb.core.service.impl;

import com.alibaba.china.jweb.core.constant.JwebConstant;
import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.Parameter;
import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.exception.ComponentException;
import com.alibaba.china.jweb.core.exception.WidgetException;
import com.alibaba.china.jweb.core.model.LoopModel;
import com.alibaba.china.jweb.core.model.WidgetParameterModel;
import com.alibaba.china.jweb.core.repository.ComponentDao;
import com.alibaba.china.jweb.core.repository.WidgetDao;
import com.alibaba.china.jweb.core.service.ComponentService;
import com.alibaba.china.jweb.core.service.WidgetService;
import com.alibaba.china.jweb.core.util.JacksonUtil;
import com.alibaba.china.jweb.core.util.WidgetUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.lang.reflect.Type;
import java.util.*;

/**
 * com.alibaba.china.jweb.core.service.impl:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 9:40 AM
 */
@Service
public class WidgetServiceImpl implements WidgetService {
    @Autowired
    private WidgetDao widgetDao;
    @Autowired
    private ComponentService componentService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Widget add(Long parentId, String componentCode) {
        Assert.notNull(parentId);
        Assert.notNull(componentCode);
        Widget widget = new Widget();
        widget.setComponentCode(componentCode);

        widget = add(widget);
        Widget parent = widgetDao.findOne(parentId);
        String children = parent.getElementChildren();
        children = WidgetUtil.addChildren(children, widget.getId());
        parent.setElementChildren(children);
        if(widget.getId() == null){
            widget.setGmtCreate(new Date());
        }
        widget.setGmtModified(new Date());
        widgetDao.save(parent);
        return widget;
    }

    public Widget add(Widget widget) {
        Assert.notNull(widget);
        widget.setVersion(JwebConstant.DEFAULT_VERSION);
        String code = widget.getComponentCode();
        Map<String, String> widgetParameters = new HashMap<String, String>();
        Component component = componentService.getByCode(code);
        if (component == null) {
            new ComponentException("can not find!id:" + component.getId() + ";code:" + component.getCode());
        }
        widget.setComponentCode(component.getCode());
        widget.setType(component.getType());
        List<Parameter> parameters = componentService.getParametersByCode(component.getCode());
        if (parameters != null && parameters.size() > 0) {
            for (Parameter parameter : parameters) {
                if (parameter.getLoop() != null && parameter.getLoop()) {
                    continue;
                }
                widgetParameters.put(parameter.getName(), parameter.getDefaultValue());
            }
            String widgetParameter = JacksonUtil.toJson(widgetParameters);
            widget.setParameters(widgetParameter);
        }
        if(widget.getId() == null){
            widget.setGmtCreate(new Date());
        }
        widget.setGmtModified(new Date());
        return widgetDao.save(widget);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void move(Long id, Long from, Long to) {
        Assert.notNull(id);
        Assert.notNull(from);
        Assert.notNull(to);
        Widget widget = widgetDao.findOne(id);
        if (widget == null) {
            throw new WidgetException("can not find!id:" + id);
        }
        Widget fromWidget = widgetDao.findOne(from);
        if (fromWidget == null) {
            throw new WidgetException("can not find!id:" + from);
        }
        Widget toWidget = widgetDao.findOne(to);
        if (toWidget == null) {
            throw new WidgetException("can not find!id:" + to);
        }
        String fromChildren = WidgetUtil.removeChildren(fromWidget.getElementChildren(), id);
        fromWidget.setElementChildren(fromChildren);

        String toChildren = WidgetUtil.addChildren(toWidget.getElementChildren(), id);
        toWidget.setElementChildren(toChildren);
        fromWidget.setGmtModified(new Date());
        toWidget.setGmtModified(new Date());
        widgetDao.save(fromWidget);
        widgetDao.save(toWidget);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void remove(Long parentId, Long id) {
        Widget parent = widgetDao.findOne(parentId);
        String children = WidgetUtil.removeChildren(parent.getElementChildren(), id);
        parent.setElementChildren(children);
        parent.setGmtModified(new Date());
        widgetDao.save(parent);
    }

    public Widget saveParameter(Long id, Map<String, String> param) {
        Assert.notNull(id);
        Assert.notNull(param);
        Widget widget = widgetDao.findOne(id);
        if (widget == null) {
            throw new WidgetException("can not find!id:" + id);
        }
        Map<String,String> p = JacksonUtil.toObject(widget.getParameters(),new TypeReference<Map<String, String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        if(p != null && !p.isEmpty()){
            p.putAll(param);
        }
        String parameters = JacksonUtil.toJson(p);
        widget.setParameters(parameters);
        if(widget.getId() == null){
            widget.setGmtCreate(new Date());
        }
        widget.setGmtModified(new Date());
        return widgetDao.save(widget);
    }

    public WidgetParameterModel findPamameterById(Long id) {
        Assert.notNull(id);
        Widget widget = widgetDao.findOne(id);
        if (widget == null) {
            throw new WidgetException("can not find!id:" + id);
        }
        String paramJson = widget.getParameters();
        Map<String, String> paramMap = JacksonUtil.toObject(paramJson, new TypeReference<Map<String, String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        if (paramMap == null) {
            paramMap = new HashMap<String, String>();
        }
        List<Parameter> parameterList = componentService.getParametersByCode(widget.getComponentCode());
        if (parameterList == null || parameterList.isEmpty()) {
            return null;
        }
        List<LoopModel> loopList = new ArrayList<LoopModel>();
        List<Parameter> parameters = new ArrayList<Parameter>();
        for (Parameter parameter : parameterList) {
            if (parameter.getLoop() == null || !parameter.getLoop()) {
                String defaultValue = paramMap.get(parameter.getName());
                if (StringUtils.isNotBlank(defaultValue)) {
                    parameter.setDefaultValue(defaultValue);
                }
                parameters.add(parameter);
            } else {
                LoopModel loopModel = new LoopModel();
                loopModel.setCode(parameter.getCode());
                loopModel.setName(parameter.getName());
                loopModel.setType(parameter.getType());
                List<Parameter> p = componentService.getParametersByCode(parameter.getCode());
                if(p != null && p.size() > 0){
                    loopModel.setParameters(p);
                }
                loopList.add(loopModel);
            }
        }
        return new WidgetParameterModel(parameters, loopList);
    }

    public List<Map<String,?>> getLoopListById(Long id,String loopName){
        Widget widget = widgetDao.findOne(id);
        String loopJson = widget.getLoopChildren();
        List<Map<String,?>> result = new ArrayList<Map<String,?>>();
        Map<String,List<Long>> loopMap = new HashMap<String, List<Long>>();

        if(StringUtils.isNotBlank(loopJson)){
            loopMap = JacksonUtil.toObject(loopJson,new TypeReference<Map<String,List<Long>>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
        }
        if(loopMap == null){
            return result;
        }
        List<Long> loopIdList = loopMap.get(loopName);
        if(loopIdList == null || loopIdList.isEmpty()){
            return result;
        }
        for(Long wid : loopIdList){
            Widget w = widgetDao.findOne(wid);
            String ps = w.getParameters();
            if(StringUtils.isBlank(ps)){
                continue;
            }
            result.add(JacksonUtil.toObject(ps,new TypeReference<Map<String, ?>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            }));
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Widget addLoop(Long parentId, String loopName, String componentCode,Map<String,String> map) {
        Assert.notNull(parentId);
        Assert.notNull(loopName);
        Assert.notNull(componentCode);
        Widget widget = new Widget();
        widget.setComponentCode(componentCode);
        widget = add(widget);
        map.put("id",String.valueOf(widget.getId()));
        String parameters = JacksonUtil.toJson(map);
        widget.setParameters(parameters);
        widget.setGmtModified(new Date());
        widgetDao.save(widget);
        Widget parent = widgetDao.findOne(parentId);
        String loopChildren = parent.getLoopChildren();
        Map<String, List<Long>> loopMap = JacksonUtil.toObject(loopChildren, new TypeReference<LinkedHashMap<String, List<Long>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        if (loopMap == null) {
            loopMap = new LinkedHashMap<String, List<Long>>();
        }
        List<Long> loopList = loopMap.get(loopName);
        if (loopList == null) {
            loopList = new ArrayList<Long>();
        }
        loopList.add(widget.getId());
        loopMap.put(loopName, loopList);
        loopChildren = JacksonUtil.toJson(loopMap);
        parent.setLoopChildren(loopChildren);
        parent.setGmtModified(new Date());
        widgetDao.save(parent);
        return widget;
    }

    public void removeLoop(Long parentId, String loopName, Long id) {
        Widget parent = widgetDao.findOne(parentId);
        String children = WidgetUtil.removeLoopChildren(parent.getLoopChildren(), loopName,id);
        parent.setLoopChildren(children);
        parent.setGmtModified(new Date());
        widgetDao.save(parent);
    }

    public List buildTree(Long parentId,boolean isRoot){
        Widget widget = widgetDao.findOne(parentId);
        if(widget == null){
            throw new WidgetException("can not find widget!id:"+parentId);
        }
        List result = new ArrayList();
        if(isRoot){
            result.add(createTreeNode(parentId, 0L, widget));
            return result;
        }
        String childrenJson = widget.getElementChildren();
        List<Long> childrenList = WidgetUtil.getChildrenList(childrenJson);
        for(Long id : childrenList){
            Widget child = widgetDao.findOne(id);
            if(child == null){
                continue;
            }

            result.add(createTreeNode(id ,parentId,child));
        }
        return result;
    }

    private Map createTreeNode(Long id ,Long parentId,Widget widget){
        Map map = new HashMap();
        map.put("id",id);
        String name = "";
        if(StringUtils.isNotBlank(widget.getName())){
            name = widget.getName();
        }else{
            Map<String,String> p = JacksonUtil.toObject(widget.getParameters(),new TypeReference<Map<String, String>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
            if(StringUtils.isNotBlank(p.get("name"))){
                name = p.get("name");
            }
        }
        map.put("name",widget.getId()+"-"+name+"("+widget.getType()+")");
        map.put("pId",parentId);
        map.put("isParent","true");
        return map;
    }

    public Widget draggable(Long id,String left,String top){
        Widget widget = widgetDao.findOne(id);
        Map<String,String> parameter = JacksonUtil.toObject(widget.getParameters(), new TypeReference<Map<String, String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        parameter.put("left",left);
        parameter.put("top",top);
        widget.setParameters(JacksonUtil.toJson(parameter));
        widget.setGmtModified(new Date());
        widgetDao.save(widget);
        return widget;
    }

    public Widget resizable(Long id,String width,String height){
        Widget widget = widgetDao.findOne(id);
        Map<String,String> parameter = JacksonUtil.toObject(widget.getParameters(), new TypeReference<Map<String, String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        parameter.put("width",width);
        parameter.put("height",height);
        widget.setParameters(JacksonUtil.toJson(parameter));
        widget.setGmtModified(new Date());
        widgetDao.save(widget);
        return widget;
    }
}
