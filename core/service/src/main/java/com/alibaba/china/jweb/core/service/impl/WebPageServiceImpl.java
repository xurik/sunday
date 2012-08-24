package com.alibaba.china.jweb.core.service.impl;

import com.alibaba.china.jweb.core.constant.JwebConstant;
import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.WebPage;
import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.exception.WebPageException;
import com.alibaba.china.jweb.core.exception.WidgetException;
import com.alibaba.china.jweb.core.repository.ComponentDao;
import com.alibaba.china.jweb.core.repository.WebPageDao;
import com.alibaba.china.jweb.core.repository.WidgetDao;
import com.alibaba.china.jweb.core.service.ComponentService;
import com.alibaba.china.jweb.core.service.TemplateService;
import com.alibaba.china.jweb.core.service.WebPageService;
import com.alibaba.china.jweb.core.service.WidgetService;
import com.alibaba.china.jweb.core.tree.Node;
import com.alibaba.china.jweb.core.util.WidgetUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.service.impl:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 3:15 PM
 */
@Service
public class WebPageServiceImpl implements WebPageService {
    @Autowired
    private WebPageDao webPageDao;
    @Autowired
    private WidgetDao widgetDao;
    @Autowired
    private WidgetService widgetService;
    @Autowired
    private ComponentDao componentDao;
    @Autowired
    private TemplateService templateService;

    public List<WebPage> list(String webAppName) {
        return webPageDao.findByWebAppCodeOrderByIdDesc(webAppName);
    }

    public WebPage save(WebPage webPage) {
        Assert.notNull(webPage);
        if (webPage.getId() == null || webPage.getId().intValue() == 0) {
            Widget widget = addWidget(webPage);
            if (widget == null) {
                throw new WidgetException("widget can not null!webPage uri : " + webPage.getCode());
            }
            webPage.setWidgetId(widget.getId());
        }
        webPage = webPageDao.save(webPage);
        return webPage;
    }

    private Widget addWidget(WebPage webPage) {
        Component component = componentDao.findByCode(webPage.getTheme());
        Widget widget = new Widget();
        widget.setType(component.getType());
        widget.setComponentCode(component.getCode());
        widget.setVersion(JwebConstant.DEFAULT_VERSION);
        return widgetService.add(widget);
    }

    public WebPage renderPage(String webAppCode, String uri, Map parameter) {
        Assert.notNull(webAppCode);
        Assert.notNull(uri);
        WebPage webPage = webPageDao.findByWebAppCodeAndUri(webAppCode, uri);
        if (webPage == null) {
            throw new WebPageException("can not find page,please check the url!webAppCode:" + webAppCode + ";uri:" + uri);
        }
        Long widgetId = webPage.getWidgetId();
        if (widgetId == null || widgetId == null) {
            throw new WebPageException("can not find page,please check the url!webAppCode:" + webAppCode + ";uri:" + uri);
        }
        Widget widget = widgetDao.findOne(widgetId);
        Node<Widget> node = new Node<Widget>(widget);
        buildTree(node);
        templateService.renderHtml(node, parameter);
        return webPage;
    }


    private void buildTree(Node<Widget> node) {
        Widget data = node.getData();
        List<Long> children = WidgetUtil.getChildrenList(data.getElementChildren());
        List<Node<Widget>> nodeList = new ArrayList<Node<Widget>>();
        for (Long id : children) {
            Widget widget = widgetDao.findOne(id);
            Node<Widget> childNode = new Node<Widget>(widget);
            if (node.getLeft() == null) {
                node.setLeft(childNode);
            }
            nodeList.add(childNode);
        }
        Node<Widget> tmp = node.getLeft();
        for (Node<Widget> widgetNode : nodeList) {
            tmp = addRight(tmp, widgetNode);
            buildTree(widgetNode);
        }
    }

    private Node<Widget> addRight(Node<Widget> node, Node<Widget> right) {
        while (true) {
            if (node.getRight() != null && node.getData().getId().longValue() == right.getData().getId().longValue()) {
                addRight(node.getRight(), right);
            } else {
                node.setRight(right);
                return right;
            }
        }
    }
}
