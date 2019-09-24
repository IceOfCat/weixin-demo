package com.adiosava.weixin.service.impl;

import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.pojo.dto.WechatTemplatMsgDTO;
import com.adiosava.weixin.qq.weixin.WechatEventKey;
import com.adiosava.weixin.qq.weixin.WechatEventType;
import com.adiosava.weixin.qq.weixin.WechatMsgType;
import com.adiosava.weixin.service.WechatEventService;
import com.adiosava.weixin.service.WechatTemplateService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.XMLConverUtil;

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

    /**
     *重复通知过滤
     */
    private static ExpireKey expireKey = new DefaultExpireKey();

    @Override
    public boolean processEvent(HttpServletRequest request)throws IOException {
        log.info("处理微信推送");
        ServletInputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            //转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
            log.info(JSON.toJSONString(eventMessage));
            String key = eventMessage.getFromUserName() + "__"
                    + eventMessage.getToUserName() + "__"
                    + eventMessage.getMsgId() + "__"
                    + eventMessage.getCreateTime();
            if (expireKey.exists(key)) {
                log.info("推送重复");
                //重复通知不作处理
                return false;
            } else {
                expireKey.add(key);
            }
            /**
             * 消息处理
             */
            switch (eventMessage.getMsgType()){
                case  WechatMsgType.EVENT:
                    processEventMsg(eventMessage);
                    break;
                default:
            }
            return true;
        }
        return false;
    }

    /**
     * 处理事件
     * @param eventMessage
     */
    private void processEventMsg(EventMessage eventMessage) {
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
    private void processClickEvent(EventMessage eventMessage) {
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
