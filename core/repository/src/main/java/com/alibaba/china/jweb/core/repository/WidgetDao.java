package com.alibaba.china.jweb.core.repository;

import com.alibaba.china.jweb.core.entity.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * com.alibaba.china.jweb.core.repository:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 9:41 AM
 */
public interface WidgetDao extends CrudRepository<Widget,Long> {
    List<Widget> findByIdIn(List<Long> ids);
}
