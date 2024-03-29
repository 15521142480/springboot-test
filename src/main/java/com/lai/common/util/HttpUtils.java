package com.lai.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpUtils {

    public static void main(String[] args) {


        System.out.println(Base64Utils.getBase64ByImgUrl("http://localhost:1119/APMobile/assets/images/icon-1.png"));

    }

    /**
     * 根据get请求的url地址去请求链接返回信息
     * @param url
     * @return
     */
    public static String doGet(String url) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {

            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            //httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ClientProtocolException e) {
            LogUtils.httpClientError("调用get链接请求出错!: " + url, e);
        } catch (IOException e) {
            LogUtils.httpClientError("调用get链接请求出错!!!: " + url, e);
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LogUtils.httpClientError("doGet:" + url, e);
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    LogUtils.httpClientError("doGet:" + url, e);
                }
            }
        }
        return result;
    }

    /**
     * 根据get请求的url地址去请求链接返回信息,带请求头信息
     * @param url
     * @return
     */
    public static String doGet(String url, Map<String, Object> headMap) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {

            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            //httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            if (null != headMap && headMap.size() > 0) {
                Set<Entry<String, Object>> entrySet = headMap.entrySet();
                // 循环遍历，获取迭代器
                Iterator<Entry<String, Object>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> mapEntry = iterator.next();
                    httpGet.setHeader(mapEntry.getKey(), mapEntry.getValue().toString());
                }
            }
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ClientProtocolException e) {
            LogUtils.httpClientError("调用get链接请求出错!:" + url + ",headMap:" + headMap, e);
        } catch (IOException e) {
            LogUtils.httpClientError("调用get链接请求出错!!!:" + url + ",headMap:" + headMap, e);
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LogUtils.httpClientError("调用get链接请求出错!:" + url + ",headMap:" + headMap, e);
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    LogUtils.httpClientError("调用get链接请求出错!:" + url + ",headMap:" + headMap, e);
                }
            }
        }
        return result;
    }

    /**
     * 根据post请求的url地址去请求链接返回信息
     * @param url
     * @return
     */
    public static String doPost(String url, Map<String, Object> paramMap, Map<String, Object> headMap) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(20000)// 设置连接请求超时时间
                .setSocketTimeout(20000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        //httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        if (null != headMap && headMap.size() > 0) {
            Set<Entry<String, Object>> entrySet = headMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                httpPost.addHeader(mapEntry.getKey(), mapEntry.getValue().toString());
            }
        }

        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LogUtils.httpClientError("调用post链接请求出错!:" + url + ",headMap:" + headMap, e);
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (ClientProtocolException e) {
            LogUtils.httpClientError("调用post链接请求出错!:" + url + ",headMap:" + headMap, e);
        } catch (IOException e) {
            LogUtils.httpClientError("调用post链接请求出错!!!:" + url + ",headMap:" + headMap, e);
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    LogUtils.httpClientError("调用post链接请求出错!:" + url + ",headMap:" + headMap, e);
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    LogUtils.httpClientError("调用post链接请求出错!:" + url + ",headMap:" + headMap, e);
                }
            }
        }
        return result;
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @param headMap
     * @return
     */
    public static String doPost(String url, String params, Map<String, Object> headMap) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        if (null != headMap && headMap.size() > 0) {
            Set<Entry<String, Object>> entrySet = headMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                httpPost.setHeader(mapEntry.getKey(), mapEntry.getValue().toString());
            }
        }
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity,"UTF-8");
                return jsonString;
            } else {
                LogUtils.httpClientError("调用post链接请求出错!:" + url + ",headMap:" + headMap);
            }
        } catch (ClientProtocolException e) {
            LogUtils.httpClientError("调用post链接请求出错!:" + url + ",headMap:" + headMap, e);
        } catch (IOException e) {
            LogUtils.httpClientError("调用post链接请求出错!!!:" + url + ",headMap:" + headMap, e);
        }finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    LogUtils.httpClientError("调用post链接请求出错!:" + url + ",headMap:" + headMap, e);
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                LogUtils.httpClientError("调用post链接请求出错!:" + url + ",headMap:" + headMap, e);
            }
        }
        return null;
    }
}