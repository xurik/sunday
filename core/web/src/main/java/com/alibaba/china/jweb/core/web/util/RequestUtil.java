package com.alibaba.china.jweb.core.web.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.web.util:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/22/12
 * Time: 10:06 PM
 */
public class RequestUtil {
    public static Map getPamaterMap(HttpServletRequest request){
        Map parameters = new HashMap();
        for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
            String key = e.nextElement();
            String[] parameterValues = request.getParameterValues(key);
            String parameterValue = request.getParameter(key);
            if (parameterValues != null && parameterValues.length > 1) {
                for (int i = 0; i < parameterValues.length; i++) {
                    String v = parameterValues[i];
                    //v = HtmlFastEntities.HTML40.escape(v);
                    //v = KeyFilter.chkFilter(v, false);
                    parameterValues[i] = v;
                }
                parameters.put(key, parameterValues);
            } else if (StringUtils.isNotBlank(parameterValue)) {
                //parameterValue = HtmlFastEntities.HTML40.escape(parameterValue);
                //parameterValue = KeyFilter.chkFilter(parameterValue, false);
                parameters.put(key, parameterValue);
            }
        }
        return parameters;
    }
}
