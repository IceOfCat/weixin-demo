package com.adiosava.weixin.util.web;

import com.adiosava.weixin.controller.wechat.WechatMenuController;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @Author yuxudong
 * @Date 2019/9/21 13:56
 * @Version 1.0
 **/
@Slf4j
public class HttpClient {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param params
     *            请求参数
     * @return 请求结果
     */
    public static String sendGet(String url, Map<String, Object> params) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlParams= getUrlParamsFromMap(params);
            String urlNameString=url;
            if(StringUtils.isNotEmpty(urlParams)){
                urlNameString =urlNameString + "?" + getUrlParamsFromMap(params);
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
           log.error(e.getMessage(), e);
        }finally {// 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                log.error(e2.getMessage(), e2);
            }
        }
        return result.toString();
    }

    /**
     * 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param params 请求参数。
     * @return 响应结果
     */
    public static String sendPost(String url, Map<String, Object> params) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(getUrlParamsFromMap(params));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {// 使用finally块来关闭输出流、输入流
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
        return result.toString();
    }

    public static String sendJsonPost(String url,Map<String,Object> params) {
        PrintWriter out = null;
        BufferedReader in;
        OutputStream os=null;
        StringBuilder result = new StringBuilder();
        String param = getUrlParamsJson(params);
        try {
            URL httpurl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; "
                    + "Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("connection", "Keep-Alive");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            httpConn.connect();
            os = httpConn.getOutputStream();
            out = new PrintWriter(os);
            out.print(param);
            out.flush();
            out.close();
            os.close();
            in= new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(String.format("url:%s,param:%s,message:%s", url, param, e.getMessage()), e);
        }finally {
            out.close();
            try {
                os.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return result.toString();
    }

    /**
     * description:将map转换成url参数格式: name1=value1&name2=value2
     *
     * @param map
     * @return
     */
    public static String getUrlParamsFromMap(Map<String, Object> map) {
        try {
            if (null != map) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    stringBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=")
                            .append(URLEncoder.encode(entry.getValue().toString(), "UTF-8")).append("&");
                }
                String content = stringBuilder.toString();
                if (content.endsWith("&")) {
                    content = StringUtils.substringBeforeLast(content, "&");
                }
                return content;
            }
        } catch (Exception e) {
           log.error(e.getMessage(), e);
        }
        return "";
    }


    public static String snedJsonPost(String url,Object object){
        String  result="";
        if(null==object){
            return result;
        }
        String jsonParam= JSON.toJSONString(object);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(method);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                result = EntityUtils.toString(response.getEntity());
            }
            return result;
        } catch (IOException e) {
            log.info(e.getMessage());
            return result;
        }
    }


    public static String getUrlParamsJson(Map<String, Object> params){
        Set<String> keySet = params.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        StringBuilder sb = new StringBuilder();
        for (String key : keyList) {
            String value = params.get(key).toString();
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            sb.append(key);
            sb.append("=");
            try {
                sb.append(URLEncoder.encode(params.get(key).toString(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


}
