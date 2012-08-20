/*
 * Copyright 1999-2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */

package com.alibaba.china.jweb.core.entity;

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
@Entity(name = "JWEB_COMPONENT")
public class Component extends BaseEntity{
    @Column(name="NAME")
    private String name;
    @Column(name="DISPLAY")
    private String display;
    @Column(name="XTYPE")
    private String type;
    @Column(name="DESCRIPTION")
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="TEMPLATE_ID")
    private Template template;
    @OneToMany(mappedBy = "component",targetEntity=Parameter.class,fetch=FetchType.EAGER)
    private List<Parameter> parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
