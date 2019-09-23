package com.adiosava.weixin.service;

import com.adiosava.weixin.pojo.dto.WeChatRequestDTO;
import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.pojo.dto.WechatTemplatMsgDTO;

/**
 * 微信模板接口
 * @Author yuxudong
 * @Date 2019/9/23 8:58
 * @Version 1.0
 **/
public interface WechatTemplateService {

    /**
     *  设置所属行业
     * @param requestDTO
     * @return
     */
    WeChatResponseDTO setIndustry(WeChatRequestDTO requestDTO);

    /**
     * 获得模板ID
     * @return
     */
    WeChatResponseDTO getTemplateId(WeChatRequestDTO requestDTO);

    /**
     * 获取模板列表
     * @return
     */
    WeChatResponseDTO getAllTemplat();

    /**
     * 删除模板
     * @return
     */
    WeChatResponseDTO delTemplate(WeChatRequestDTO requestDTO);

    WeChatResponseDTO send(WechatTemplatMsgDTO templatMsgDTO );

}
