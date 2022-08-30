package com.lai.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 字符串工具
 * @Author: laixues
 * @Date: 2021/1/12
 */
public class StringUtils {

    /**
     * 是空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (!"null".equals(str) && str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    /**
     * 非空
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    /**
     * 根据Object返回数字
     * @param num
     * @return
     */
    public static Integer getNumByObject(Object num) {
        return Integer.parseInt(String.valueOf(num));
    }




    /**
     * 根据字符串数组获取
     * @param values
     * @return
     */
    public static String concat(String[] values) {
        if (values == null) {
            return null;
        } else {
            StringBuffer buffer = new StringBuffer();
            String[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String value = var2[var4];
                if (buffer.length() > 0) {
                    buffer.append(",");
                }

                buffer.append(value);
            }

            return buffer.toString();
        }
    }

    /**
     * 根据字符串文件流处理成map
     * @param inputStr
     * @return
     */
    public static Map<String, Object> parseJsonParameters(String inputStr) {
        if (inputStr == null) {
            return null;
        } else {
            try {
                Map<String, Object> dataMap = null;
                JSONObject jsonData = JSON.parseObject(inputStr);
                if (jsonData != null) {
                    dataMap = new HashMap<>();
                    Iterator var3 = jsonData.keySet().iterator();

                    while(var3.hasNext()) {
                        String key = (String)var3.next();
                        dataMap.put(key, jsonData.get(key));
                    }
                }

                return dataMap;
            } catch (Exception var5) {
                return null;
            }
        }
    }

    /**
     * 根据url的字符串文件流处理成map
     * @param inputStr
     * @return
     */
    public static Map<String, Object> parseUrlParameters(String inputStr) {

        Map<String, Object> dataMap = new HashMap<>();
        if (inputStr == null) {
            return null;
        } else {
            int charCount = countChar(inputStr, '%');
            if (inputStr.contains("%26") || inputStr.contains("%23") || inputStr.contains("%40") || inputStr.contains("%3D") || charCount > 2) {
                try {
                    inputStr = URLDecoder.decode(inputStr, "UTF-8");
                } catch (UnsupportedEncodingException var6) {
                }
            }

            String[] segments = split(inputStr, "&");

            for (String segment : segments) {
                int begin = segment.indexOf("=");
                if (begin > 0) {
                    dataMap.put(segment.substring(0, begin), segment.substring(begin + 1));
                }
            }

            return dataMap;
        }
    }
    public static int countChar(String input, char ch) {
        int count = 0;
        if (input == null) {
            return 0;
        } else {
            for(int i = 0; i < input.length(); ++i) {
                if (input.charAt(i) == ch) {
                    ++count;
                }
            }

            return count;
        }
    }
    public static String[] split(String aStr, String split) {
        String[] columnNames = StringUtils.split(aStr, split);
        if (columnNames != null) {
            for(int i = 0; i < columnNames.length; ++i) {
                if (columnNames[i] != null) {
                    columnNames[i] = columnNames[i].trim();
                } else {
                    columnNames[i] = "";
                }
            }
        }

        return columnNames;
    }

    public static String getRandomFileName() {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return rannum + str;// 当前时间
    }


    /**
     * 根据字符获取字节分割串
     * @param value
     * @return
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

}
