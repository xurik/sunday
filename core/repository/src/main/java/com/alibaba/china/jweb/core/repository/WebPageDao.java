package com.alibaba.china.jweb.core.repository;

import com.alibaba.china.jweb.core.entity.WebPage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * com.alibaba.china.jweb.core.repository:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 3:17 PM
 */
public interface WebPageDao extends CrudRepository<WebPage, Long> {
    List<WebPage> findByWebAppCodeOrderByIdDesc(String webAppCode);

    WebPage findByWebAppCodeAndUri(String webAppCode, String uri);
}
