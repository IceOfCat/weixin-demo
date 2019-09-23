package com.adiosava.weixin.service;

import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.pojo.dto.WechatMenuDTO;

/**
 * 微信公众号菜单接口
 * @Author yuxudong
 * @Date 2019/9/21 15:16
 * @Version 1.0
 **/
public interface WechatMenuService {

    /**
     * 创建菜单
     * @param menuDTO
     */
    WeChatResponseDTO createMenu(WechatMenuDTO menuDTO);
}
