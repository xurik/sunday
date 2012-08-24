package com.alibaba.china.jweb.core.service;

import com.alibaba.china.jweb.core.entity.Parameter;
import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.model.WidgetParameterModel;

import java.util.Map;

/**
 * com.alibaba.china.jweb.core.service:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 5:18 PM
 */
public interface WidgetService {
    Widget add(Long parentId, String componentCode);

    Widget add(Widget widget);

    void move(Long id, Long from, Long to);

    void remove(Long parentId, Long id);

    Widget saveParameter(Long id, Map<String, String> param);

    WidgetParameterModel findPamameterById(Long id);

    Widget addLoop(Long parentId, String loopName, String componentCode);

    void removeLoop(Long parentId, String loopName, Long id);
}
