/*
 * Copyright 1999-2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */

package com.alibaba.china.jweb.core.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;


/**
 * com.alibaba.china.jweb.core.entity:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 2:31 PM
 */
@Entity
@Table(name = "JWEB_WIDGET")
public class Widget extends BaseEntity {

    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "XTYPE")
    private String type;
    @Column(name = "VERSION")
    private String version;
    @Column(name = "PARAMETERS")
    private String parameters;
    @Column(name = "LOOP_CHILDREN")
    private String loopChildren;
    @Column(name = "NUM")
    private String num;
    @Column(name = "COMPONENT_CODE")
    private String componentCode;
    @Column(name = "ELEMENT_CHILDREN")
    private String elementChildren;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getLoopChildren() {
        return loopChildren;
    }

    public void setLoopChildren(String loopChildren) {
        this.loopChildren = loopChildren;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getComponentCode() {
        return componentCode;
    }

    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    public String getElementChildren() {
        return elementChildren;
    }

    public void setElementChildren(String elementChildren) {
        this.elementChildren = elementChildren;
    }
}
