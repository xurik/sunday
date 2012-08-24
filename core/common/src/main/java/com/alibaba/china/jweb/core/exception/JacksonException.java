/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.alibaba.china.jweb.core.exception;

/**
 * 类JacksonException.java的实现描述：TODO 类实现描述
 *
 * @author ri.xur Jul 30, 2012 4:36:56 PM
 */
public class JacksonException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6946381182898887797L;

    public JacksonException() {
        super();
    }

    public JacksonException(String msg) {
        super(msg);
    }

    public JacksonException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JacksonException(Throwable cause) {
        super(cause);
    }
}
