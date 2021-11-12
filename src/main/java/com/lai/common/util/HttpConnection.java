package com.lai.common.util;


import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpConnection {

    public static final String Error_Code_Create_Http = "error-create-http";
    public static final String Error_Code_Http_Non200 = "error-http-status";
    public static final String Error_Code_Read_Data = "error-read-data";
    public static final String Error_Code_None = "ok";

    public static HttpURLConnection build(String url) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        } catch (Exception e) {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
            return null;
        }

        if (url.toLowerCase().startsWith("https://")) {
            HttpsURLConnection connection = (HttpsURLConnection) httpURLConnection;
            try {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, new TrustManager[]{new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }

                    public void checkClientTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    public void checkServerTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }
                }}, new java.security.SecureRandom());
                SSLSocketFactory newFactory = sc.getSocketFactory();
                connection.setSSLSocketFactory(newFactory);
                connection.setHostnameVerifier((s, sslSession) -> true);
            } catch (Exception e) {
                e.printStackTrace();
                if (httpURLConnection != null)
                    httpURLConnection.disconnect();
                return null;
            }
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000);
            return connection;
        }

        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setConnectTimeout(60000);

        return httpURLConnection;
    }

    /**
     * @param method
     * @param url
     * @param headers
     * @param body
     *</pre>
     */
    public static String[]  request(String method, String url, String[] headers, byte[] body) {
        HttpURLConnection httpURLConnection = build(url);
        if (httpURLConnection == null)
            return new String[]{Error_Code_Create_Http, "create http request failed!"};
        try {
            httpURLConnection.setRequestMethod(method);

            if (headers != null) {
                for (int i = 0; i < headers.length; i++) {
                    String header = headers[i];
                    String value = "";
                    i++;
                    if (i < headers.length)
                        value = headers[i];
                    httpURLConnection.setRequestProperty(header, value);
                }
            }

            if (body != null) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(body);
            } else {
                httpURLConnection.setDoInput(true);
            }

            if (httpURLConnection.getResponseCode() != 200) {
                return new String[]{Error_Code_Http_Non200, String.format("code: %s, message: \"%s\".", Integer.valueOf(httpURLConnection.getResponseCode()), httpURLConnection.getResponseMessage())};
            }
            return new String[]{Error_Code_None, IOUtils.toString(httpURLConnection.getInputStream(), "utf-8")};
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            printWriter.close();
            return new String[]{Error_Code_Read_Data, stringWriter.toString()};
        } finally {
            httpURLConnection.disconnect();
        }
    }

    /**
     * @param method
     * @param url
     * @param headers
     * @param body
     *</pre>
     */
    public static String[] request(String method, String url, String[] headers, String body) {
        try {
            return request(method, url, headers, body.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // never
            return null;
        }
    }

}
