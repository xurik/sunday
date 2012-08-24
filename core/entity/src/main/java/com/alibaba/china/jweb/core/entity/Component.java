/*
 * Copyright 1999-2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */

package com.alibaba.china.jweb.core.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * com.alibaba.china.jweb.core.entity:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 10:15 AM
 */
@Entity
@Table(name = "JWEB_COMPONENT")
public class Component extends BaseEntity{
    @Column(name="CODE",unique = true)
    private String code;
    @Column(name="NAME",unique = true)
    private String name;
    @Column(name="XTYPE")
    private String type;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="THUMBNAIL")
    private String thumbnail;
    @Column(name="TEMPLATE")
    @Lob
    private String template;
    @Column(name = "RESOLVER")
    private String resolver;
    @Transient
    private List<Parameter> parameters;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
