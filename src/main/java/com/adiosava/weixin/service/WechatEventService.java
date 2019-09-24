package com.adiosava.weixin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 维信事件接口
 * @Author yuxudong
 * @Date 2019/9/23 14:24
 * @Version 1.0
 **/
public interface WechatEventService {

    /**
     * 处理事件
     * @param request
     * @param response
     */
    boolean processEvent(HttpServletRequest request)throws IOException;
}
