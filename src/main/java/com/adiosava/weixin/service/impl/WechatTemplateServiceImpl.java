package com.adiosava.weixin.service.impl;

import com.adiosava.weixin.pojo.dto.WeChatRequestDTO;
import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.pojo.dto.WechatTemplatMsgDTO;
import com.adiosava.weixin.service.WechatTemplateService;
import com.adiosava.weixin.util.web.WechatHttpClient;
import com.adiosava.weixin.util.web.WechatUrl;
import org.springframework.stereotype.Service;

/**
 * @Author yuxudong
 * @Date 2019/9/23 8:58
 * @Version 1.0
 **/
@Service
public class WechatTemplateServiceImpl implements WechatTemplateService {

    @Override
    public WeChatResponseDTO setIndustry(WeChatRequestDTO requestDTO) {
        return WechatHttpClient.sendJsonPost(WechatUrl.SET_INDUSTRY+WechatUrl.ACCESS_TOKEN, requestDTO);
    }

    @Override
    public WeChatResponseDTO getTemplateId(WeChatRequestDTO requestDTO) {
        return WechatHttpClient.sendJsonPost(WechatUrl.GET_TEMPLATE_ID+WechatUrl.ACCESS_TOKEN, requestDTO);
    }

    @Override
    public WeChatResponseDTO getAllTemplat() {
        return WechatHttpClient.sendGet(WechatUrl.GET_ALL_TEMPLATE+WechatUrl.ACCESS_TOKEN, null);
    }

    @Override
    public WeChatResponseDTO delTemplate(WeChatRequestDTO requestDTO) {
        return WechatHttpClient.sendJsonPost(WechatUrl.DEL_TEMPLATE+WechatUrl.ACCESS_TOKEN, requestDTO);
    }

    @Override
    public WeChatResponseDTO send(WechatTemplatMsgDTO templatMsgDTO) {
        return WechatHttpClient.sendJsonPost(WechatUrl.SEND_TEMPLATE_MSG+WechatUrl.ACCESS_TOKEN, templatMsgDTO);
    }

}
