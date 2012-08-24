/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.china.jweb.core.util;

import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;

import com.alibaba.china.jweb.core.exception.JacksonException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类JacksonUtil.java的实现描述：TODO 类实现描述
 *
 * @author ri.xur Jul 16, 2012 5:04:10 PM
 */
public class JacksonUtil {

    private final static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JacksonUtil() {
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @SuppressWarnings("unchecked")
    public static <T> T toObject(String content, TypeReference<T> valueTypeRef) {
        if (StringUtils.isBlank(content) || valueTypeRef == null) return null;
        try {
            return getObjectMapper().readValue(content, valueTypeRef);
        } catch (Exception e) {
            String msg = "stringToObject error!check the json!";
            logger.error(msg, e);
            throw new JacksonException(msg, e);
        }
    }

    /**
     * 将对象转换成Json字符串
     *
     * @param value 非空
     * @return
     * @throws JacksonException
     */
    public static String toJson(Object value) {
        if (value == null) return null;
        Writer writer = new StringWriter();
        try {
            getObjectMapper().writeValue(writer, value);
            return writer.toString();
        } catch (Exception e) {
            String msg = "objectToString error!value:" + value;
            logger.error(msg, e);
            throw new JacksonException(msg, e);
        } finally {
            WriterUtil.clossWriter(writer);
        }
    }
}
