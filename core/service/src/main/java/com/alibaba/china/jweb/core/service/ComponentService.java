package com.alibaba.china.jweb.core.service;

import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.Parameter;

import java.util.List;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.service:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/20/12
 * Time: 5:33 PM
 */
public interface ComponentService {
    Iterable<Component> list(Component component);

    /**
     * 保存UI组件
     *
     * @param component
     * @return
     */
    Component save(Component component);

    /**
     * 根据ID获取组件
     *
     * @param id
     * @return
     */
    Component getById(Long id);

    /**
     * 根据ID获取组件
     *
     * @param code
     * @return
     */
    Component getByCode(String code);

    /**
     * 根据Name获取组件的参数列表
     *
     * @param name
     * @return
     */
    List<Parameter> getParametersByCode(String name);

    /**
     * 保存参数
     *
     * @param parameter
     * @param componentCode
     * @return
     */
    Parameter saveParameter(Parameter parameter, String componentCode);

    /**
     * 保存模板
     *
     * @param componentCode
     * @param resolver
     * @param template
     * @return
     */
    Component saveTemplate(String componentCode, String resolver, String template);

    /**
     * 根据Type 分组
     *
     * @return
     */
    List buildTree();

}
