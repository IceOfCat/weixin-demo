package com.adiosava.weixin.pojo.dto;

import lombok.*;

/**
 * 模板消息内容
 * @Author yuxudong
 * @Date 2019/9/23 11:09
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WechatTemplateContentDTO {

    /**
     * 内容
     */
    private String value;
    /**
     * 模板内容字体颜色，不填默认为黑色
     */
    private String color;

}
