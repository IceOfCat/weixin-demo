package com.adiosava.weixin.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 微信接口回调结果
 * @Author yuxudong
 * @Date 2019/9/21 14:12
 * @Version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WeChatResponseDTO {

    /**
     * 获取到的凭证
     */
    private String access_token;

    /**
     * 凭证有效时间，单位：秒
     */
    private Integer expires_in;

    /**
     * 返回码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 模板id
     */
    private String template_id;

    /**
     * 模板列表
     */
    private List<WechatTemplatDTO> template_list;
    /**
     * 模板消息id
     */
    private String msgid;


    /**
     * 响应是否成功
     * @return
     */
    public boolean isSuccess(){
        return null != this.errcode && 0 == this.errcode && StringUtils.isNotEmpty(this.errmsg) && "ok".equals(this.errmsg);
    }


}
