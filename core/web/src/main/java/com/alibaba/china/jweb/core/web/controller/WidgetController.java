package com.alibaba.china.jweb.core.web.controller;

import com.alibaba.china.jweb.core.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.web:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/22/12
 * Time: 3:35 PM
 */
@Controller
@RequestMapping("/jweb")
public class WidgetController {
    @Autowired
    private WidgetService widgetService;
    @RequestMapping("/widget/add/{parentId}/{componentCode}")
    public void add(@PathVariable Long parentId,@PathVariable String componentCode,ModelMap modelMap){
        widgetService.add(parentId,componentCode);
        modelMap.put("success",true);
    }
    @RequestMapping("/widget/move/{parentId}/{componentCode}")
    public void move(@PathVariable Long id,@PathVariable Long from,@PathVariable Long to,ModelMap modelMap){
        widgetService.move(id, from,to);
        modelMap.put("success",true);
    }

    @RequestMapping("/widget/delete/{parentId}/{id}")
    public void remove(@PathVariable Long parentId,@PathVariable Long id,ModelMap modelMap){
        widgetService.remove(parentId,id);
        modelMap.put("success",true);
    }
    @RequestMapping("/widget/addLoop/{parentId}/{componentCode}")
    public void addLoop(@PathVariable Long parentId,@PathVariable String componentCode,ModelMap modelMap){
        //widgetService.addLoop(parentId,componentCode);
        modelMap.put("success",true);
    }

    @RequestMapping("/widget/removeLoop/{parentId}/{id}")
    public void removeLoop(@PathVariable Long parentId,@PathVariable Long id,ModelMap modelMap){
        //widgetService.removeLoop(parentId,id);
        modelMap.put("success",true);
    }
    @RequestMapping("/widget/saveParameter/{id}")
    public void saveParameter(@PathVariable Long id,Map<String,String> formData,ModelMap modelMap){
        widgetService.saveParameter(id,formData);
    }

}
