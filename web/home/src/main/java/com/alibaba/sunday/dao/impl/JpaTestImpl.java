package com.alibaba.sunday.dao.impl;

import com.alibaba.sunday.EntityTest;
import com.alibaba.sunday.dao.JpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Email:sunday.xur@gmail.com
 * Date: 8/17/12
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class JpaTestImpl implements JpaTest {
    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager manager;
    public void save() {

        EntityTest entityTest  = new EntityTest();
        //entityManagerFactory.createEntityManager()
        manager.merge(entityTest);
        Query query = manager.createQuery("select count(*) from EntityTest u where id is not null");
        CriteriaBuilder cb=manager.getCriteriaBuilder();
        cb.createQuery(entityTest.getClass());

        System.out.println(query.getResultList());
        System.out.println(query.getMaxResults());
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
