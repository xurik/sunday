package com.alibaba.china.jweb.core.web.controller;

import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.Parameter;
import com.alibaba.china.jweb.core.service.ComponentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * com.alibaba.china.jweb.core.web:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 5:13 PM
 */
@Controller
@RequestMapping("/jweb/component")
public class ComponentController {
    @Autowired
    private ComponentService componentService;


    /**
     * �������
     *
     * @param id
     * @return
     */
    @RequestMapping("/form")
    public String form(Long id) {
        return "jweb/component/form";
    }

    /**
     * ��ѯ�������
     *
     * @param component
     * @param modelMap
     */
    @RequestMapping("/list")
    public void list(Component component, ModelMap modelMap) {
        modelMap.put("rows", componentService.list(component));
    }


    /**
     * �������
     *
     * @param component
     * @param modelMap
     */
    @RequestMapping("/save")
    public void save(Component component, ModelMap modelMap) {
        component = componentService.save(component);
        modelMap.addAttribute("data", component);
        modelMap.addAttribute("success", true);
    }

    /**
     * ��ȡ���ģ��
     *
     * @param componentCode
     * @param modelMap
     * @return
     */
    @RequestMapping("/template/{componentCode}")
    public String geTemplate(@PathVariable String componentCode, ModelMap modelMap) {
        Assert.notNull(componentCode);
        modelMap.put("componentCode", componentCode);
        Component component = componentService.getByCode(componentCode);
        if (component != null) {
            if (StringUtils.isNotBlank(component.getTemplate())) {
                modelMap.put("template", component.getTemplate());
            }
            if (StringUtils.isNotBlank(component.getResolver())) {
                modelMap.put("resolver", component.getResolver());
            }

        }
        return "jweb/component/template";
    }

    /**
     * �������ģ��
     *
     * @param componentCode
     * @param resolver
     * @param template
     * @param modelMap
     */
    @RequestMapping("/saveTemplate/{componentCode}")
    public void saveParameter(@PathVariable String componentCode, String resolver, String template, ModelMap modelMap) {
        componentService.saveTemplate(componentCode, resolver, template);
        modelMap.put("success", true);
    }

    /**
     * ��ȡ�������
     *
     * @param code
     * @param modelMap
     */
    @RequestMapping("/getParameters/{code}")
    public void getParameters(@PathVariable String code, ModelMap modelMap) {
        modelMap.put("rows", componentService.getParametersByCode(code));
    }

    /**
     * �����������
     *
     * @param parameter
     * @param componentCode
     * @param modelMap
     */
    @RequestMapping("/saveParameter/{componentCode}")
    public void saveParameter(Parameter parameter, @PathVariable String componentCode, ModelMap modelMap) {
        componentService.saveParameter(parameter, componentCode);
        modelMap.put("success", true);
    }

    @RequestMapping("/componentTree")
    public void componentTree(ModelMap modelMap) {
        modelMap.put("data", componentService.buildTree());
    }

}
