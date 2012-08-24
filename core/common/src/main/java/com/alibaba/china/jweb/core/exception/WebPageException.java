package com.alibaba.china.jweb.core.exception;

/**
 * com.alibaba.china.jweb.core.exception:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/22/12
 * Time: 5:10 PM
 */
public class WebPageException extends RuntimeException {
    public WebPageException() {
        super();
    }

    public WebPageException(String msg) {
        super(msg);
    }

    public WebPageException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public WebPageException(Throwable cause) {
        super(cause);
    }
}
