package com.lai.common.util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description: 数组工具
 * @Author: laixues
 * @Date: 2021/3/2
 */
public class ArrayUtils {

    /***
     * 去除String数组中的空值
     */
    public static String[] deleteArrayNull(String[] string) {

        // 赋值新数组
        ArrayList<String> strList = new ArrayList<>();
        Collections.addAll(strList, string);

        // 删除list列表中所有的空值
        while (strList.remove(null));
        while (strList.remove(""));

        // 把list列表转换给一个新定义的中间数组，并赋值给它
        String[] strArrLast = strList.toArray(new String[strList.size()]);

        return strArrLast;
    }

}
