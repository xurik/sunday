package com.alibaba.china.jweb.core.model;

import com.alibaba.china.jweb.core.entity.Parameter;

import java.util.List;

/**
 * com.alibaba.china.jweb.core.model:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/27/12
 * Time: 11:24 PM
 */
public class LoopModel {
    private String code;
    private String name;
    private String type;
    private List<Parameter> parameters;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
