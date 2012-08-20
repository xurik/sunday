/*
 * Copyright 1999-2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */

package com.alibaba.china.jweb.core.entity;

import javax.persistence.*;

/**
 * com.alibaba.china.jweb.core.entity:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 2:31 PM
 */
@Entity(name = "JWEB_WIDGET")
public class Widget extends BaseEntity{
    @Column(name = "NAME")
    private String name;
    @Column(name="DISPLAY")
    private String display;
    @Column(name = "XTYPE")
    private String type;
    @Column(name = "VERSION")
    private String version;
    @Column(name = "PARAMETERS")
    private String parameters;
    @Column(name = "LOOP_PARAMETERS")
    private String loopParameters;
    @Column(name = "WIDGET_ORDER")
    private String order;
    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name="COMPONENT_ID")
    private Component component;
    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name="WEB_PAGE_ID")
    private WebPage webPage;

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

    public String getLoopParameters() {
        return loopParameters;
    }

    public void setLoopParameters(String loopParameters) {
        this.loopParameters = loopParameters;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public WebPage getWebPage() {
        return webPage;
    }

    public void setWebPage(WebPage webPage) {
        this.webPage = webPage;
    }
}
