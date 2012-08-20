package com.alibaba.china.jweb.core.service.impl;

import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.repository.ComponentDao;
import com.alibaba.china.jweb.core.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

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
    public Component save(Component component){
        Assert.notNull(component);
        if(component.getId() == null)
            component.setGmtCreate(new Date());
        return componentDao.save(component);
    }
    public Component findOne(Long id){
        return componentDao.findOne(id);
    }
}
