package com.alibaba.sunday;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 8/16/12
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class Test {
    @RequestMapping("/test1")
    public ModelAndView test(){
        ModelAndView view = new ModelAndView("test111");
        StringUtils.split("---");
        view.addObject("test","test");
        System.out.println("acccccccaaaaaaaaaaaaaaaaaaaaaaaaaaaacccccc");
        System.out.println("-----------------*********--------------------");
        return view;
    }
}
