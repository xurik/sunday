package com.alibaba.china.jweb.core.service;

import com.alibaba.china.jweb.core.entity.Component;

/**
 * com.alibaba.china.jweb.core.service:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 5:33 PM
 */
public interface ComponentService {
    /**
     * ����UI���
     * @param component
     * @return
     */
    Component save(Component component);
    Component findOne(Long id);
}
