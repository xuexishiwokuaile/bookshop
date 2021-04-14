package com.example.bookshop.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapperUtil {
    /**
     * 书籍类型
     */
    /*public static final Map<Integer, String> bookType;
    static {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "未分类");
        map.put(1, "文学");
        map.put(2, "哲学");
        map.put(3, "政治");
        map.put(4, "历史");
        map.put(5, "艺术");
        map.put(6, "地理");
        map.put(7, "自然科学");
        map.put(8, "综合");
        bookType = Collections.unmodifiableMap(map);
    }*/

    /**
     * 书籍状态
     */
    public static final Map<Integer, String> bookState;
    static {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "在售");
        map.put(1, "下架");
        map.put(2, "预售");
        bookState = Collections.unmodifiableMap(map);
    }

    public static final Map<Integer, String> bookIsHot;
    static {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "未知");
        map.put(1, "不热门");
        map.put(2, "热门");
        bookIsHot = Collections.unmodifiableMap(map);
    }

    public static final Map<Integer, String> userAuthority;
    static {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "管理员");
        map.put(1, "顾客");
        userAuthority = Collections.unmodifiableMap(map);
    }
}
