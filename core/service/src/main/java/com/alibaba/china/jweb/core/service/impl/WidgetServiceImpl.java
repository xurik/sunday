package com.alibaba.china.jweb.core.service.impl;

import com.alibaba.china.jweb.core.constant.JwebConstant;
import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.Parameter;
import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.exception.ComponentException;
import com.alibaba.china.jweb.core.exception.WidgetException;
import com.alibaba.china.jweb.core.model.WidgetParameterModel;
import com.alibaba.china.jweb.core.repository.ComponentDao;
import com.alibaba.china.jweb.core.repository.WidgetDao;
import com.alibaba.china.jweb.core.service.ComponentService;
import com.alibaba.china.jweb.core.service.WidgetService;
import com.alibaba.china.jweb.core.util.JacksonUtil;
import com.alibaba.china.jweb.core.util.WidgetUtil;
import com.fasterxml.jackson.core.type.TypeReference;
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
public class WidgetServiceImpl implements WidgetService{
    @Autowired
    private WidgetDao widgetDao;
    @Autowired
    private ComponentService componentService;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Widget add(Long parentId,String componentCode){
        Assert.notNull(parentId);
        Assert.notNull(componentCode);
        Widget widget = new Widget();
        widget.setComponentCode(componentCode);

        widget = add(widget);
        Widget parent = widgetDao.findOne(parentId);
        String children = parent.getElementChildren();
        children = WidgetUtil.addChildren(children, widget.getId());
        parent.setElementChildren(children);
        widgetDao.save(parent);
        return widget;
    }

    public Widget add(Widget widget){
        Assert.notNull(widget);
        widget.setVersion(JwebConstant.DEFAULT_VERSION);
        String code = widget.getComponentCode();
        Map<String,String> widgetParameters = new HashMap<String, String>();
        Component component = componentService.getByCode(code);
        if(component == null){
            new ComponentException("can not find!id:"+component.getId()+";code:"+component.getCode());
        }
        widget.setComponentCode(component.getCode());
        widget.setType(component.getType());
        List<Parameter> parameters = componentService.getParametersByCode(component.getCode());
        if(parameters != null && parameters.size()>0){
            for(Parameter parameter : parameters){
                if(parameter.getLoop() != null && parameter.getLoop()){
                    continue;
                }
                widgetParameters.put(parameter.getName(), parameter.getDefaultValue());
            }
            String widgetParameter = JacksonUtil.toJson(widgetParameters);
            widget.setParameters(widgetParameter);
        }
        return widgetDao.save(widget);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void move(Long id,Long from,Long to){
        Assert.notNull(id);
        Assert.notNull(from);
        Assert.notNull(to);
        Widget widget = widgetDao.findOne(id);
        if(widget == null){
            throw new  WidgetException("can not find!id:"+id);
        }
        Widget fromWidget = widgetDao.findOne(from);
        if(fromWidget == null){
            throw new  WidgetException("can not find!id:"+from);
        }
        Widget toWidget = widgetDao.findOne(to);
        if(toWidget == null){
            throw new  WidgetException("can not find!id:"+to);
        }
        String fromChildren = WidgetUtil.removeChildren(fromWidget.getElementChildren(),id);
        fromWidget.setElementChildren(fromChildren);

        String toChildren = WidgetUtil.addChildren(toWidget.getElementChildren(),id);
        toWidget.setElementChildren(toChildren);
        widgetDao.save(fromWidget);
        widgetDao.save(toWidget);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void remove(Long parentId,Long id){
        Widget parent = widgetDao.findOne(parentId);
        String children = WidgetUtil.removeChildren(parent.getElementChildren(),id);
        parent.setElementChildren(children);
        widgetDao.save(parent);
    }

    public Widget saveParameter(Long id,Map<String,String> param){
        Assert.notNull(id);
        Assert.notNull(param);
        Widget widget = widgetDao.findOne(id);
        if(widget == null){
            throw new  WidgetException("can not find!id:"+id);
        }
        String parameters = JacksonUtil.toJson(param);
        widget.setParameters(parameters);
        return widgetDao.save(widget);
    }

    public WidgetParameterModel findPamameterById(Long id){
        Assert.notNull(id);
        Widget widget = widgetDao.findOne(id);
        if(widget == null){
            throw new  WidgetException("can not find!id:"+id);
        }
        String paramJson = widget.getParameters();
        Map<String,String> paramMap = JacksonUtil.toObject(paramJson,new TypeReference<Map<String, String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        if(paramMap == null){
            paramMap = new HashMap<String, String>();
        }
        List<Parameter> parameterList = componentService.getParametersByCode(widget.getComponentCode());
        if(parameterList == null || parameterList.isEmpty()){
            return null;
        }
        List<String> loopName = new ArrayList<String>();
        List<Parameter> parameters = new ArrayList<Parameter>();
        for(Parameter parameter : parameterList){
            if(!parameter.getLoop()){
                String defaultValue = paramMap.get(parameter.getName());
                if(StringUtils.isNotBlank(defaultValue)){
                    parameter.setDefaultValue(defaultValue);
                }
                parameters.add(parameter);
            }else {
                loopName.add(parameter.getType());
            }
        }
        return new WidgetParameterModel(parameters,loopName);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Widget addLoop(Long parentId,String loopName,String componentCode){
        Assert.notNull(parentId);
        Assert.notNull(loopName);
        Assert.notNull(componentCode);
        Widget widget = new Widget();
        widget.setComponentCode(componentCode);
        widget = add(widget);
        Widget parent = widgetDao.findOne(parentId);
        String loopChildren = parent.getLoopChildren();
        Map<String,List<Long>> loopMap = JacksonUtil.toObject(loopChildren, new TypeReference<LinkedHashMap<String,List<Long>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        if(loopMap == null){
            loopMap = new LinkedHashMap<String, List<Long>>();
        }
        List<Long> loopList =loopMap.get(loopName);
        if(loopList == null){
            loopList = new ArrayList<Long>();
        }
        loopList.add(widget.getId());
        loopMap.put(loopName,loopList);
        loopChildren = JacksonUtil.toJson(loopName);
        parent.setLoopChildren(loopChildren);
        widgetDao.save(parent);
        return widget;
    }

    public void removeLoop(Long parentId,String loopName,Long id){
        Widget parent = widgetDao.findOne(parentId);
        String children = WidgetUtil.removeChildren(parent.getLoopChildren(),id);
        parent.setLoopChildren(children);
        widgetDao.save(parent);
    }

}
