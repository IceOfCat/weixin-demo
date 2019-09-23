package com.adiosava.weixin.service.impl;

import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.pojo.dto.WechatMenuDTO;
import com.adiosava.weixin.service.WechatMenuService;
import com.adiosava.weixin.util.web.WechatHttpClient;
import com.adiosava.weixin.util.web.WechatUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author yuxudong
 * @Date 2019/9/21 15:17
 * @Version 1.0
 **/
@Service
@Slf4j
public class WechatMenuServiceImpl implements WechatMenuService {

    @Override
    public WeChatResponseDTO createMenu(WechatMenuDTO menuDTO) {
        return   WechatHttpClient.sendJsonPost(WechatUrl.SET_MENU_CREATE+WechatUrl.ACCESS_TOKEN,menuDTO);
    }
}
