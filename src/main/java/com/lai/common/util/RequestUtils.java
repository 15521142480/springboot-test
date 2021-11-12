package com.lai.common.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

/**
 * @Description: 请求模块
 * @Author: laixues
 * @Date: 2021/1/28
 */
public class RequestUtils {


    /**
     * 把请求的全部参数封装成map
     * @param request
     * @return
     * @throws IOException
     */
    @SneakyThrows
    public static Map<String, Object> getParamsByRequest(HttpServletRequest request) {

        Map<String, Object> params = new HashMap<>();

        request.setCharacterEncoding("UTF-8");
        String contentType = request.getContentType();
        String method = request.getMethod();

        if (null != contentType && contentType.indexOf("application/json") == 0 && "Post".equalsIgnoreCase(method)) { // post请求参数

            String queryParams = IOUtils.toString(request.getInputStream(), "utf-8");

            if (queryParams == null || queryParams.length() == 0) { // 处理非文件对象

                JSONObject jsonParams = new JSONObject();
                Enumeration names = request.getParameterNames();

                while(names.hasMoreElements()) {

                    String paramName = (String)names.nextElement(); // key
                    if (!"v,time,nonce,token".contains(paramName)) {
                        if (request.getParameter(paramName) != null) {
                            jsonParams.put(paramName, request.getParameter(paramName));
                        } else {
                            jsonParams.put(paramName, StringUtils.concat(request.getParameterValues(paramName)));
                        }
                    }
                }

                queryParams = jsonParams.toJSONString();
            }

            Map tmpMap = StringUtils.parseJsonParameters(queryParams);
            if (tmpMap == null) {
                tmpMap = StringUtils.parseUrlParameters(queryParams);
            }

            params.putAll(tmpMap);

        } else { // 处理没有请求头或者是get请求

            Enumeration names = request.getParameterNames();
            while(names.hasMoreElements()) {

                String name = String.valueOf(names.nextElement());
                String value = request.getParameter(name);
                params.put(name, value);
            }
        }

        // paramMap.put("curUserId", getUserId(request));

        return params;
    }



}
