package com.adiosava.weixin.pojo.dto;

import lombok.*;

/**
 * @Author yuxudong
 * @Date 2019/9/20 16:03
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WeChatRequestDTO {
    /**
     *微信加密签名
     */
    private String signature;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 随机
     */
    private String nonce;
    /**
     * 随机字符串
     */
    private String echostr;
    /**
     *公众号模板消息所属行业编号
     */
    private  String industry_id1;
    /**
     *公众号模板消息所属行业编号
     */
    private  String industry_id2;
    /**
     * 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     */
    private  String template_id_short;
    /**
     * 公众帐号下模板消息ID
     */
    private String template_id;



}
