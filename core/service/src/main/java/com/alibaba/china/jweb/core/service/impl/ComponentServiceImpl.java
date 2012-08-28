package com.alibaba.china.jweb.core.service.impl;

import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.Parameter;
import com.alibaba.china.jweb.core.model.WidgetParameterModel;
import com.alibaba.china.jweb.core.repository.ComponentDao;
import com.alibaba.china.jweb.core.repository.ParameterDao;
import com.alibaba.china.jweb.core.service.ComponentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * com.alibaba.china.jweb.core.service.impl:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 5:33 PM
 */
@Service
public class ComponentServiceImpl implements ComponentService {
    @Autowired
    private ComponentDao componentDao;
    @Autowired
    private ParameterDao parameterDao;

    public Iterable<Component> list(Component component) {
        return componentDao.getAll();
    }

    public Component save(Component component) {
        Assert.notNull(component);
        if (component.getId() == null)
            component.setGmtCreate(new Date());
        component.setGmtModified(new Date());
        return componentDao.save(component);
    }

    public Component getById(Long id) {
        return componentDao.findOne(id);
    }

    public Component getByCode(String code) {
        return componentDao.findByCode(code);
    }

    public List<Parameter> getParametersByCode(String code) {
        Component component = componentDao.findByCode(code);
        if (component == null) {
            return (List<Parameter>) CollectionUtils.EMPTY_COLLECTION;
        }
        return getParametersByCode(component, new HashMap<String, Parameter>());
    }

    private List<Parameter> getParametersByCode(Component component,Map<String,Parameter> map){
        List<Parameter> parameters = parameterDao.findByComponentCodeOrderByIdDesc(component.getCode());
        if(parameters != null && parameters.size()>0){
            for(Parameter parameter : parameters){
                if(!map.containsKey(parameter.getCode())){
                    map.put(parameter.getCode(),parameter);
                }
            }
        }
        if(StringUtils.isNotBlank(component.getExtend())){
            Component parent = componentDao.findByCode(component.getExtend());
            getParametersByCode(parent,map);
        }
        return new ArrayList<Parameter>(map.values());
    }

    public Parameter saveParameter(Parameter parameter, String componentCode) {
        Assert.notNull(parameter);
        Assert.notNull(componentCode);
        Component component = componentDao.findByCode(componentCode);
        Assert.notNull(component);
        parameter.setComponentCode(component.getCode());
        if(parameter.getId() == null){
            parameter.setGmtCreate(new Date());
        }
        parameter.setGmtModified(new Date());
        return parameterDao.save(parameter);
    }

    public Component saveTemplate(String componentCode, String resolver, String template) {
        Assert.notNull(componentCode);
        Component component = getByCode(componentCode);
        if (component == null || StringUtils.isBlank(resolver) || StringUtils.isBlank(template)) {
            return null;
        }
        component.setResolver(resolver);
        component.setTemplate(template);
        component.setGmtModified(new Date());
        return componentDao.save(component);
    }

    public List buildTree() {
        List<String> typies = componentDao.getTypeList();
        List tree = new ArrayList();
        for (String type : typies) {
            Map typeMap = new HashMap();
            typeMap.put("name",type);
            List children = new ArrayList();
            List<Component> list = componentDao.findByType(type);
            for (Component child : list) {
                Map childMap = new HashMap();
                Map attr = new HashMap();
                childMap.put("id",child.getCode());
                childMap.put("name",child.getName());
                children.add(childMap);
            }
            typeMap.put("children", children);
            tree.add(typeMap);
        }
        return tree;
    }

}
