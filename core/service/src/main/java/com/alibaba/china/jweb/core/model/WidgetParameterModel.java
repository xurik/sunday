package com.alibaba.china.jweb.core.model;

import com.alibaba.china.jweb.core.entity.Parameter;

import java.util.List;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.model:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 11:11 AM
 */
public class WidgetParameterModel {
    private List<Parameter> parameters;
    private List<LoopModel> loopList;

    public WidgetParameterModel(List<Parameter> parameters, List<LoopModel> loopList) {
        this.parameters = parameters;
        this.loopList = loopList;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<LoopModel> getLoopList() {
        return loopList;
    }

    public void setLoopList(List<LoopModel> loopList) {
        this.loopList = loopList;
    }
}
