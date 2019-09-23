package com.adiosava.weixin.service.impl;

import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.pojo.dto.WechatEventMessageDTO;
import com.adiosava.weixin.pojo.dto.WechatTemplatMsgDTO;
import com.adiosava.weixin.qq.weixin.WechatEventKey;
import com.adiosava.weixin.qq.weixin.WechatEventType;
import com.adiosava.weixin.service.WechatEventService;
import com.adiosava.weixin.service.WechatTemplateService;
import com.adiosava.weixin.util.XmlUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yuxudong
 * @Date 2019/9/23 14:25
 * @Version 1.0
 **/
@Slf4j
@Service
public class WechatEventServiceImpl implements WechatEventService {

    @Autowired
    private WechatTemplateService wechatTemplateService;

    @Override
    public boolean processEvent(HttpServletRequest request, HttpServletResponse response)throws IOException {
        log.info("处理微信推送");
        ServletInputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            //转换XML
            WechatEventMessageDTO eventMessage = XmlUtils.convertToObject(WechatEventMessageDTO.class, inputStream);

            log.info("接收xml为："+JSON.toJSONString(eventMessage));
            processMsg(eventMessage);
            return true;
        }
        return false;
    }

    /**
     * 处理事件
     * @param eventMessage
     */
    private void processMsg(WechatEventMessageDTO eventMessage) {
        switch (eventMessage.getEvent()){
            case WechatEventType.CLICk:
                processClickEvent(eventMessage);
                break;
            default:
        }
    }

    /**
     * 处理点击事件
     * @param eventMessage
     */
    private void processClickEvent(WechatEventMessageDTO eventMessage) {
        log.info("处理点击事件");
        switch (eventMessage.getEventKey()){
            case  WechatEventKey.SEND_PIC_KEY:
                log.info("发送消息");
                WechatTemplatMsgDTO msgDTO=WechatTemplatMsgDTO.builder().touser(eventMessage.getFromUserName())
                        .template_id("ocTHdsMw5DT4T3UNL6Tcx3TDAtcXGHMkhFQlOwIMSiI").build();
                WeChatResponseDTO result = wechatTemplateService.send(msgDTO);
                log.info("发送结果为:"+JSON.toJSONString(result));
            default:
        }
    }


}
