package com.alibaba.china.jweb.core.repository;

import com.alibaba.china.jweb.core.entity.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * com.alibaba.china.jweb.core.repository:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 12:07 AM
 */
public interface ParameterDao extends CrudRepository<Parameter,Long>{
    List<Parameter> findByComponentCodeOrderByIdDesc(String componentCode);
}
