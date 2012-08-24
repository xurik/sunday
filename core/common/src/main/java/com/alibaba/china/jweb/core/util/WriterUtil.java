/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.china.jweb.core.util;

import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类WriterUtil.java的实现描述：TODO 类实现描述
 * 
 * @author ri.xur Jul 30, 2012 4:40:23 PM
 */
public class WriterUtil {

    private final static Logger logger = LoggerFactory.getLogger(WriterUtil.class);

    public static void clossWriter(Writer writer) {
        if (writer == null) return;
        try {
            writer.close();
        } catch (IOException e) {
            logger.error("clossWriter error!", e);
        }
    }
}
