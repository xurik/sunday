package com.alibaba.china.jweb.core.web.controller;

import com.alibaba.china.jweb.core.entity.WebPage;
import com.alibaba.china.jweb.core.service.TemplateService;
import com.alibaba.china.jweb.core.service.WebPageService;
import com.alibaba.china.jweb.core.web.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.web:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 2:49 PM
 */
@Controller
public class WebPageController {
    @Autowired
    private WebPageService webPageService;
    @Autowired
    private TemplateService templateService;

    /**
     * 页面管理
     *
     * @param webApp
     * @param webPage
     * @param modelMap
     * @return
     */
    @RequestMapping("/{webApp}/webpage/manage")
    public String manage(@PathVariable String webApp, WebPage webPage, ModelMap modelMap) {
        modelMap.put("webApp", webApp);
        return "jweb/webpage/manage";
    }

    /**
     * 页面列表
     *
     * @param webApp
     * @param modelMap
     */
    @RequestMapping("/{webApp}/webpage/list")
    public void list(@PathVariable String webApp, ModelMap modelMap) {
        List<WebPage> list = webPageService.list(webApp);
        modelMap.put("rows", list);
    }

    /**
     * 保存页面
     *
     * @param webApp
     * @param webPage
     * @param modelMap
     */
    @RequestMapping("/{webApp}/webpage/save")
    public void save(@PathVariable String webApp, WebPage webPage, ModelMap modelMap) {
        Assert.notNull(webApp, "webApp can not null!");
        Assert.notNull(webPage, "WebPage can not null!webApp:" + webApp);
        webPage.setWebAppCode(webApp);
        webPageService.save(webPage);
        modelMap.put("success", true);
    }

    /**
     * 渲染页面
     *
     * @param webApp
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/{webApp}/**", "/{webApp}/**/**", "/{webApp}/**/**/**", "/{webApp}/**/**/**/**"})
    public String renderPage(@PathVariable String webApp, HttpServletRequest request, ModelMap modelMap) {
        String uri = request.getRequestURI();
        uri = StringUtils.replace(uri, "/" + webApp + "/", "");
        VelocityContext velocityContext = new VelocityContext();
        Map pamaterMap = RequestUtil.getPamaterMap(request);
        WebPage webPage = webPageService.renderPage(webApp, uri, pamaterMap);
        if (webPage != null) {
            modelMap.put("J_WEB_PAGE", webPage);
        }
        modelMap.put("widget",webPage.getHtml());
        return "jweb/webpage/render";
    }
}
