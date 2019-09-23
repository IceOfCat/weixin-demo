package com.adiosava.weixin.service;

import com.adiosava.weixin.pojo.dto.WeChatRequestDTO;
import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;

/**
 * 微信公众号接口
 * @Author yuxudong
 * @Date 2019/9/20 15:58
 * @Version 1.0
 **/
public interface WeChatOfficialAccountService {

    /**
     * 校验请求的安全性
     * @param weChatRequestDTO
     * @return
     */
    Boolean checkSignature(WeChatRequestDTO weChatRequestDTO);

    /**
     * 获取公众号的AccessToken
     * @return
     */
    WeChatResponseDTO getAccessToken();

}
