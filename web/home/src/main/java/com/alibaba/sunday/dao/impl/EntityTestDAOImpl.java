package com.alibaba.sunday.dao.impl;

import com.alibaba.sunday.dao.EntityTestDAOCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Email:sunday.xur@gmail.com
 * Date: 8/17/12
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityTestDAOImpl implements EntityTestDAOCustom{
    @PersistenceContext
    private EntityManager entityManager ;
    public void findByName(){
        entityManager.createQuery("select u from EntityTest u").getResultList();

    }

}
