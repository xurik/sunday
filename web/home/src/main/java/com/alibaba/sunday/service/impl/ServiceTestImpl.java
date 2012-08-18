package com.alibaba.sunday.service.impl;

import com.alibaba.sunday.EntityTest;
import com.alibaba.sunday.dao.EntityTestDAO;
import com.alibaba.sunday.dao.JpaTest;
import com.alibaba.sunday.service.ServiceTest;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Email:sunday.xur@gmail.com
 * Date: 8/17/12
 * Time: 12:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Component

public class ServiceTestImpl implements ServiceTest {
    @Autowired
    private EntityTestDAO entityTestDAO;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(){
        //entityTestDAO.deleteAll();
        for(int i=0;i<100;i++){
            EntityTest entityTest  = new EntityTest();
            entityTest.setName("name");
            //entityTestDAO.save(entityTest);
            entityTestDAO.findByName();
            //entityTestDAO.save(entityTest);
            //entityTestDAO.sharedCustomMethod();
            //jpaTest.save();
            //entityTestDAO.findAll();
               if(i==30){
                  // throw new RuntimeException("--------------------------");
               }
//            EntityTest entityTest1 = entityTestDAO.get(entityTest.getId());
//            System.out.println(entityTest1.getId()+":"+entityTest1.getName());
        }

    }
}
