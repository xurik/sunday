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

/**
 * com.alibaba.china.jweb.core.entity:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 3:39 PM
 */
@Entity(name="JWEB_WEB_PAGE")
public class WebPage extends BaseEntity{
    @Column(name = "NAME")
    private String name;
    @Column(name="DISPLAY")
    private String display;
    @Column(name="URL")
    private String url;
    @Column(name="OWNER")
    private String owner;
    @Column(name="DESCRIPTION")
    private String description;
    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name="WEB_APP_ID")
    private WebApp webApp;
    @OneToMany(mappedBy = "webPage",targetEntity=Widget.class,fetch=FetchType.LAZY)
    private List<Widget> widgets;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public WebApp getWebApp() {
        return webApp;
    }

    public void setWebApp(WebApp webApp) {
        this.webApp = webApp;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }
}
