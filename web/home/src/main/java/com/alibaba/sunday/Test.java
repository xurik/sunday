package com.alibaba.sunday;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 8/16/12
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class Test {
    @Autowired
    @Qualifier("dataSource")
    private BasicDataSource dataSource;
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    @RequestMapping("/test1")
    public ModelAndView test(){
        ModelAndView view = new ModelAndView("test111");
        StringUtils.split("---");
        view.addObject("test","test");
        System.out.println(sessionFactory.getCache()+"-----------------------------------");
        try {
            System.out.println(dataSource.getConnection().getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return view;
    }
}
