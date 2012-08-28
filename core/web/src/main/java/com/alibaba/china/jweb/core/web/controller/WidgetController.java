package com.alibaba.china.jweb.core.web.controller;

import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.model.WidgetParameterModel;
import com.alibaba.china.jweb.core.service.WidgetService;
import com.alibaba.china.jweb.core.web.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
    public void add(@PathVariable Long parentId, @PathVariable String componentCode, ModelMap modelMap) {
        widgetService.add(parentId, componentCode);
        modelMap.put("success", true);
    }

    @RequestMapping("/widget/move/{parentId}/{componentCode}")
    public void move(@PathVariable Long id, @PathVariable Long from, @PathVariable Long to, ModelMap modelMap) {
        widgetService.move(id, from, to);
        modelMap.put("success", true);
    }

    @RequestMapping("/widget/delete/{parentId}/{id}")
    public void remove(@PathVariable Long parentId, @PathVariable Long id, ModelMap modelMap) {
        widgetService.remove(parentId, id);
        modelMap.put("success", true);
    }

    @RequestMapping("/widget/loop/save/{parentId}/{loopName}/{type}")
    public void addLoop(@PathVariable Long parentId, @PathVariable String loopName,@PathVariable String type,HttpServletRequest request, ModelMap modelMap) {
        Map<String,String> map = RequestUtil.getPamaterMap(request);
        String oper = request.getParameter("oper");
        if(StringUtils.equals("add",oper)){
            widgetService.addLoop(parentId, loopName, type,map);
        }else if(StringUtils.equals("edit",oper)){
            String id = request.getParameter("id");
            if(StringUtils.isNotBlank(id)){
                widgetService.saveParameter(Long.valueOf(id),map);
            }
        }else if(StringUtils.equals("del",oper)){
            String id = request.getParameter("id");
            if(StringUtils.isNotBlank(id)){
                widgetService.removeLoop(parentId,loopName,Long.valueOf(id));
            }
        }

        modelMap.put("success", true);
    }

    @RequestMapping("/widget/removeLoop/{parentId}/${loopName}/{id}")
    public void removeLoop(@PathVariable Long parentId, @PathVariable String loopName, @PathVariable Long id, ModelMap modelMap) {
        widgetService.removeLoop(parentId, loopName, id);
        modelMap.put("success", true);
    }

    @RequestMapping("/widget/parameter/{id}")
    public String getParameter(@PathVariable Long id, ModelMap modelMap){
        WidgetParameterModel widgetParameterModel = widgetService.findPamameterById(id);
        modelMap.put("parameters",widgetParameterModel.getParameters());
        modelMap.put("loops",widgetParameterModel.getLoopList());
        return "/jweb/widget/configs";
    }

    @RequestMapping("/widget/loop/list/{id}/{loopName}")
    public void getLoopListById(@PathVariable Long id, @PathVariable String loopName,ModelMap modelMap){
        List<Map<String,?>> list = widgetService.getLoopListById(id,loopName);
        modelMap.put("rows",list);
    }

    @RequestMapping("/widget/saveParameter/{id}")
    public void saveParameter(@PathVariable Long id, HttpServletRequest request, ModelMap modelMap) {
        Map<String,String> map = RequestUtil.getPamaterMap(request);
        widgetService.saveParameter(id, map);
        modelMap.put("success", true);
    }

    @RequestMapping("/widget/buildtree/{parentId}")
    public void buildtree(@PathVariable Long parentId, HttpServletRequest request,ModelMap modelMap) {
        String id = request.getParameter("id");
        if(StringUtils.isNotBlank(id)){
            parentId = Long.valueOf(id);
        }
        List list = widgetService.buildTree(parentId);
        modelMap.put("data",list);
        modelMap.put("success", true);
    }

}
