package com.alibaba.china.jweb.core.web;

import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.Parameter;
import com.alibaba.china.jweb.core.service.ComponentService;
import net.sf.cglib.beans.BeanMap;
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

    @RequestMapping("/form")
    public String form(Long id){
        return "jweb/component/form";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id,ModelMap modelMap){
        Assert.notNull(id);
        Component component = componentService.findOne(id);
        if(component != null){
            modelMap.putAll(BeanMap.create(component));
        }

        return "jweb/component/form";
    }
    @RequestMapping("/save")
    public void save(Component component,ModelMap modelMap){
        component = componentService.save(component);
        modelMap.addAttribute("data",component);
        modelMap.addAttribute("success",true);
    }

    public void addParameter(Parameter parameter,Long componentId){

    }
}
