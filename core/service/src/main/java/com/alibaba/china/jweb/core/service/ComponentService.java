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
     * ����UI���
     *
     * @param component
     * @return
     */
    Component save(Component component);

    /**
     * ����ID��ȡ���
     *
     * @param id
     * @return
     */
    Component getById(Long id);

    /**
     * ����ID��ȡ���
     *
     * @param code
     * @return
     */
    Component getByCode(String code);

    /**
     * ����Name��ȡ����Ĳ����б�
     *
     * @param name
     * @return
     */
    List<Parameter> getParametersByCode(String name);

    /**
     * �������
     *
     * @param parameter
     * @param componentCode
     * @return
     */
    Parameter saveParameter(Parameter parameter, String componentCode);

    /**
     * ����ģ��
     *
     * @param componentCode
     * @param resolver
     * @param template
     * @return
     */
    Component saveTemplate(String componentCode, String resolver, String template);

    /**
     * ����Type ����
     *
     * @return
     */
    List buildTree();

}
