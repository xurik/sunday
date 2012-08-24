package com.alibaba.china.jweb.core.exception;

/**
 * com.alibaba.china.jweb.core.exception:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/22/12
 * Time: 5:10 PM
 */
public class ComponentException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 6946381182898887797L;

    public ComponentException(){
        super();
    }

    public ComponentException(String msg){
        super(msg);
    }

    public ComponentException(String msg, Throwable cause){
        super(msg, cause);
    }

    public ComponentException(Throwable cause){
        super(cause);
    }
}
