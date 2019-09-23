package com.adiosava.weixin.pojo.dto;

import lombok.*;

import java.util.Map;

/**
 * @Author yuxudong
 * @Date 2019/9/23 11:03
 * @Version 1.0
 **/
/**
 * 模板消息
 *注：url和miniprogram都是非必填字段，若都不传则模板无跳转；
 * 若都传，会优先跳转至小程序。开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WechatTemplatMsgDTO {

    /**
     * 接收者openid
     */
    private String touser;
    /**
     * 模板ID
     */
    private String template_id;

    /**
     * 模板跳转链接（海外帐号没有跳转能力）
     */
    private String url;

    private WeChatMinprogramDTO miniprogram;

    private Map<String,WechatTemplateContentDTO> data;

}
