package com.aguo.wxpush.utils;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpUtil {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数,请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        OutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Accept", "application/json;charset=UTF-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            conn.connect();
            out = conn.getOutputStream();
            // 发送请求参数
            out.write(param.getBytes("UTF-8"));
            // flush输出流的缓冲
            out.flush();
            out.close();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {

            //System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    private static OkHttpClient httpClient = new OkHttpClient.Builder().build();

    public static byte[] doGet(String url, Map<String, String[]> header, Map<String, String[]> params, String expectContentType) {
        Request.Builder builder = new Request.Builder();
        addHeader(builder, header);
        addUrlParam(builder, url, params);
        return requestExec(builder.build(), expectContentType);
    }

    public static byte[] doPost(String url, Map<String, String[]> header, Map<String, String[]> body, String expectContentType) {
        Request.Builder builder = new Request.Builder().url(url);
        addHeader(builder, header);
        addBodyParam(builder, body, "POST");
        return requestExec(builder.build(), expectContentType);
    }

    private static void addHeader(Request.Builder builder, Map<String, String[]> header) {
        if (header == null) {
            return;
        }
        for (String key : header.keySet()) {
            String[] values = header.get(key);
            if (values != null) {
                for (String value : values) {
                    builder.addHeader(key, value);
                }
            }
        }
    }

    private static void addUrlParam(Request.Builder builder, String url, Map<String, String[]> params) {
        if (params == null) {
            return;
        }
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            if (values != null) {
                for (String value : values) {
                    urlBuilder.addQueryParameter(key, value);
                }
            }
        }
        builder.url(urlBuilder.build());
    }

    private static void addBodyParam(Request.Builder builder, Map<String, String[]> body, String method) {
        if (body == null) {
            return;
        }
        FormBody.Builder formBodyBuilder = new FormBody.Builder(StandardCharsets.UTF_8);
        for (String key : body.keySet()) {
            String[] values = body.get(key);
            if (values != null) {
                for (String value : values) {
                    formBodyBuilder.add(key, value);
                }
            }
        }
        builder.method(method, formBodyBuilder.build());
    }

    private static byte[] requestExec(Request request, String expectContentType) {
        Objects.requireNonNull(request, "okHttp request is null");

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.code() == 200) {
                ResponseBody body = response.body();
                if (body != null) {
                    String contentType = response.header("Content-Type");
                    if (contentType != null && !contentType.contains(expectContentType)) {
                        String res = new String(body.bytes(), StandardCharsets.UTF_8);
                        System.out.println(res);
                        return null;
                    }
                    return body.bytes();
                }
                System.out.println("response body is null");
            } else {
                System.out.println("request failed, http code: " + response.code());
            }
        } catch (IOException ioException) {
            System.out.println("request exec error: " + ioException.getMessage());
        }
        return null;
    }
}
