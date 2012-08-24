package com.alibaba.china.jweb.core.service;

import com.alibaba.china.jweb.core.entity.WebPage;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.service:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 3:15 PM
 */
public interface WebPageService {
    List<WebPage> list(String webAppName);
    WebPage save(WebPage webPage);
    WebPage renderPage(String webAppName,String url,Map parameter);
}
