package com.alibaba.china.jweb.core.plugin;

import com.alibaba.china.jweb.core.entity.Component;
import com.alibaba.china.jweb.core.entity.Parameter;
import com.alibaba.china.jweb.core.entity.Widget;
import com.alibaba.china.jweb.core.repository.WidgetDao;
import com.alibaba.china.jweb.core.service.ComponentService;
import com.alibaba.china.jweb.core.service.WidgetService;
import com.alibaba.china.jweb.core.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.plugin:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 5:32 PM
 */
public class Table {
    @Autowired
    private WidgetDao widgetDao;
    @Autowired
    private ComponentService componentService;
    @Autowired
    private WidgetService widgetService;

    public void execute(Widget widget, String componentCode) {
        widget = widgetService.add(widget);

    }
}
