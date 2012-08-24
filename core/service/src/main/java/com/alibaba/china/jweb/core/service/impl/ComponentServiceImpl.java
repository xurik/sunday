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
        return parameterDao.findByComponentCodeOrderByIdDesc(code);
    }

    public Parameter saveParameter(Parameter parameter, String componentCode) {
        Assert.notNull(parameter);
        Assert.notNull(componentCode);
        Component component = componentDao.findByCode(componentCode);
        Assert.notNull(component);
        parameter.setComponentCode(component.getCode());
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
        return componentDao.save(component);
    }

    public List buildTree() {
        List<String> typies = componentDao.getTypeList();
        List tree = new ArrayList();
        for (String type : typies) {
            Map typeMap = new HashMap();
            typeMap.put("data", type);
            List children = new ArrayList();
            List<Component> list = componentDao.findByType(type);
            for (Component child : list) {
                Map childMap = new HashMap();
                Map attr = new HashMap();
                childMap.put("data", child.getName());
                attr.put("id", child.getId());
                attr.put("code", child.getCode());
                childMap.put("attr", attr);
                children.add(childMap);
            }
            typeMap.put("children", children);
            tree.add(typeMap);
        }
        return tree;
    }

}
