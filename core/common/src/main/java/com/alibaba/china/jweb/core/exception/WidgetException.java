package com.alibaba.china.jweb.core.exception;

/**
 * com.alibaba.china.jweb.core.exception:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/22/12
 * Time: 5:10 PM
 */
public class WidgetException extends RuntimeException {
    public WidgetException() {
        super();
    }

    public WidgetException(String msg) {
        super(msg);
    }

    public WidgetException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public WidgetException(Throwable cause) {
        super(cause);
    }
}
