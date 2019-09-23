package com.adiosava.weixin.pojo.dto;

import lombok.*;

import javax.ejb.StatefulTimeout;

/**
 * 小程序
 * @Author yuxudong
 * @Date 2019/9/23 11:06
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WeChatMinprogramDTO {

    /**
     * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
     */
    private String appid;

    /**
     * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏
     */
    private String pagepath;

}
