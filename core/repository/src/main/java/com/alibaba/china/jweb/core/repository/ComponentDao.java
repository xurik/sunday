package com.alibaba.china.jweb.core.repository;

import com.alibaba.china.jweb.core.entity.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * com.alibaba.china.jweb.core.repository:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 5:12 PM
 */
public interface ComponentDao extends PagingAndSortingRepository<Component, Long> {
    Component findByCode(String code);

    @Query("select u from Component u order by id desc ")
    List<Component> getAll();

    @Query("select u.type from Component u group by u.type order by u.type")
    List<String> getTypeList();

    @Query("select u from Component u where u.type = ?1 order by u.id")
    List<Component> findByType(String type);
}
