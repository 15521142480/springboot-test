package com.lai.common.util;

import org.apache.log4j.Logger;


/**
 * 根据不同的需求和功能模块输出日志到相应的文件
 *
 * @author yangbingz
 */
public class LogUtils {

    protected static Logger log = Logger.getLogger(LogUtils.class); // 控制台的打印日志
    protected static Logger httpClientLog = Logger.getLogger("httpClientLog"); // 调用外部接口的报错

    // 是否开启日志输出的开关
    public static boolean flag = true;
    public static boolean httpClient = true;


    public static void debug(String msg) {
        if (flag) {
            log.debug(msg);
        }
    }

    public static void debug(String msg, Exception e) {
        if (flag) {
            log.debug(msg, e);
        }
    }

    public static void info(String msg) {
        if (flag) {
            log.info(msg);
        }
    }

    public static void info(String msg, Exception e) {
        if (flag) {
            log.info(msg, e);
        }
    }

    public static void error(String msg) {
        if (flag) {
            log.error(msg);
        }
    }

    public static void error(String msg, Exception e) {
        if (flag) {
            log.error(msg, e);
        }
    }

    public static void httpClientError(String msg) {
        if (httpClient) {
            httpClientLog.error(msg);
        }
    }

    public static void httpClientError(String msg, Exception e) {
        if (httpClient) {
            httpClientLog.error(msg, e);
        }
    }

}
