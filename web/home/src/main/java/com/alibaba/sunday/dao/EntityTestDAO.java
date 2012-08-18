package com.alibaba.sunday.dao;

import com.alibaba.sunday.EntityTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Email:sunday.xur@gmail.com
 * Date: 8/16/12
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EntityTestDAO extends Repository<EntityTest,Long>,EntityTestDAOCustom {
    EntityTest findById(Long id);
    EntityTest findByName(String name);
    //List<EntityTest> findByAndAnd(String and);
}
