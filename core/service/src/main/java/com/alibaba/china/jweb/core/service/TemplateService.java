package com.alibaba.china.jweb.core.service;

import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.tree.Node;

import java.util.Map;

/**
 * com.alibaba.china.jweb.core.service:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 11:00 PM
 */
public interface TemplateService {
    String renderHtml(Node<Widget> node, Map parameter);
}
