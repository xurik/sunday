/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.china.jweb.core.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 类VelocityUtil.java的实现描述：TODO 类实现描述
 * 
 * @author ri.xur Jul 19, 2012 4:49:09 PM
 */
public class VelocityUtil {

    /**
     * 获取数组的第index个值,超长返回最后一个
     * 
     * @param values
     * @param index
     * @return
     */
    public String getByIndex(String[] values, int index) {
        if (values == null || values.length == 0) return "";
        if (index >= values.length) {
            index = values.length - 1;
        }
        return values[index];
    }

    /**
     * 获取数组的第index个值,超长返回最后一个
     * 
     * @param values
     * @param index
     * @return
     */
    public String getBySplitIndex(String str, String separatorChar, int index) {

        if (StringUtils.isBlank(str) || StringUtils.isBlank(separatorChar)) return "";
        String[] values = StringUtils.split(str, separatorChar);
        if (values == null || values.length == 0) return "";
        if (index >= values.length) {
            index = values.length - 1;
        }
        return values[index];
    }

    public String[] split(String str, String separatorChar) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(separatorChar)) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return StringUtils.split(str, separatorChar);
    }

    public String defaultString(String value, String defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int arrayLength(String[] array) {
        if (array == null) {
            return 0;
        } else {
            return array.length;
        }
    }

    public boolean isNotNull(Object value) {
        if (value != null && StringUtils.isNotBlank(value.toString())) {
            return true;
        }
        return false;
    }
}
