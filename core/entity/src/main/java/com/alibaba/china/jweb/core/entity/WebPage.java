/*
 * Copyright 1999-2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */

package com.alibaba.china.jweb.core.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

/**
 * com.alibaba.china.jweb.core.entity:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 3:39 PM
 */
@Entity
@Table(name = "JWEB_WEB_PAGE")
public class WebPage extends BaseEntity {
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "THEME")
    private String theme;
    @Column(name = "URI")
    private String uri;
    @Column(name = "OWNER")
    private String owner;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "WEB_APP_CODE")
    private String webAppCode;
    @Column(name = "WIDGET_ID")
    private Long widgetId;
    @Transient
    private String html;

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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebAppCode() {
        return webAppCode;
    }

    public void setWebAppCode(String webAppCode) {
        this.webAppCode = webAppCode;
    }

    public Long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Long widgetId) {
        this.widgetId = widgetId;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
