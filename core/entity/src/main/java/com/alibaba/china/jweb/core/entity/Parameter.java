/*
 * Copyright 1999-2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */

package com.alibaba.china.jweb.core.entity;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * com.alibaba.china.jweb.core.entity:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 11:44 AM
 */
@Entity
@Table(name = "JWEB_COMPONENT_PARAMETER")
public class Parameter extends BaseEntity{
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "XTYPE")
    private String type;
    @Column(name="COMPONENT_CODE")
    private String componentCode;
    @Column(name = "IS_LOOP")
    private Boolean isLoop;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "VALUE_LIST")
    private String valueList;
    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;
    @Column(name = "IS_REQUIRED")
    private Boolean isRequired;
    @Column(name = "VALIDATOR")
    private String validator;

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

    public String getComponentCode() {
        return componentCode;
    }

    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    public Boolean getLoop() {
        return isLoop;
    }

    public void setLoop(Boolean loop) {
        isLoop = loop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValueList() {
        return valueList;
    }

    public void setValueList(String valueList) {
        this.valueList = valueList;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }
}
