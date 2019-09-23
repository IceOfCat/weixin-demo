package com.adiosava.weixin.util.web;

import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 微信接口请求
 * @Author yuxudong
 * @Date 2019/9/21 14:14
 * @Version 1.0
 **/
@Slf4j
public class WechatHttpClient {

    public static WeChatResponseDTO sendGet(String url, Map<String,Object> params){
        String result = HttpClient.sendGet(url, params);
        return getResult(result);
    }

    public static WeChatResponseDTO  sendPost(String url, Map<String,Object> params){
        String result = HttpClient.sendPost(url, params);
        return getResult(result);
    }

    public static WeChatResponseDTO sendJsonPost(String url, Object object){
        String result = HttpClient.snedJsonPost(url, object);
        return getResult(result);
    }

    public static WeChatResponseDTO getResult(String resultStr){
        try {
            return  new Gson().fromJson(resultStr, new TypeToken<WeChatResponseDTO>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
