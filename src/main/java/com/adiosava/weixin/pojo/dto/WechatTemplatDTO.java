package com.adiosava.weixin.pojo.dto;

import lombok.*;

/**
 * 模板
 * @Author yuxudong
 * @Date 2019/9/23 10:45
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WechatTemplatDTO {
    /**
     * 模板ID
     */
    private String template_id;
    /**
     * 模板标题
     */
    private String title;
    /**
     * 	模板所属行业的一级行业
     */
    private String primary_industry;
    /**
     * 模板所属行业的二级行业
     */
    private String deputy_industry;
    /**
     * 模板内容
     */
    private String content;
    /**
     *
     */
    private String example;

}
