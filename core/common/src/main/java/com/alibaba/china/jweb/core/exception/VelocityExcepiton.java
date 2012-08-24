package com.alibaba.china.jweb.core.exception;

/**
 * com.alibaba.china.jweb.core.exception:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/21/12
 * Time: 11:13 PM
 */
public class VelocityExcepiton extends RuntimeException{

    public VelocityExcepiton(){
        super();
    }

    public VelocityExcepiton(String msg){
        super(msg);
    }

    public VelocityExcepiton(String msg, Throwable cause){
        super(msg, cause);
    }

    public VelocityExcepiton(Throwable cause){
        super(cause);
    }

}
