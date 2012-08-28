package com.alibaba.china.jweb.core.util;

import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * com.alibaba.china.jweb.core.util:
 * User: ri.xur
 * Email:sunday.xur@gmail.com
 * Date: 8/23/12
 * Time: 10:51 AM
 */
public class WidgetUtil {

    public static Map<String, List<Long>> getLoopChildren(String src) {
        Map<String, List<Long>> childrenMap = JacksonUtil.toObject(src, new TypeReference<LinkedHashMap<String, List<Long>>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return childrenMap;
    }

    public static String removeLoopChildren(String src, String loopName, Long child) {
        Map<String, List<Long>> childrenMap = getLoopChildren(src);
        List<Long> list = childrenMap.get(loopName);
        if (list == null)
            return "";
        Integer index = null;
        for (int i = 0; i < list.size(); i++) {
            if (child.longValue() == list.get(i).longValue()) {
                index = i;
                break;
            }
        }
        if (index != null) {
            list.remove(index.intValue());
        }
        childrenMap.put(loopName, list);
        return JacksonUtil.toJson(childrenMap);
    }

    public static String addLoopChildren(String src, String loopName, Long child) {
        Map<String, List<Long>> childrenMap = getLoopChildren(src);
        List<Long> list = childrenMap.get(loopName);
        if (list == null) {
            list = new ArrayList<Long>();
        }
        list.add(child);
        childrenMap.put(loopName, list);
        return JacksonUtil.toJson(childrenMap);
    }


    public static String removeChildren(String src, Long child) {
        List<Long> childList = getChildrenList(src);
        if (childList.isEmpty()) {
            return src;
        }
        Integer index = null;
        for (int i = 0; i < childList.size(); i++) {
            if (childList.get(i).longValue() == child.longValue()) {
                index = i;
                break;
            }
        }
        if (index != null) {
            childList.remove(index.intValue());
        }
        return JacksonUtil.toJson(childList);
    }

    public static String addChildren(String src, Long child) {
        List<Long> childList = getChildrenList(src);
        childList.add(child);
        return JacksonUtil.toJson(childList);
    }

    public static List<Long> getChildrenList(String src) {
        List<Long> childList = JacksonUtil.toObject(src, new TypeReference<List<Long>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        if (childList == null) {
            childList = new ArrayList<Long>();
        }
        return childList;
    }
}
