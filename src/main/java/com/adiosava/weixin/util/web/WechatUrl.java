package com.adiosava.weixin.util.web;

/**
 * @Author yuxudong
 * @Date 2019/9/21 14:02
 * @Version 1.0
 **/
public class WechatUrl {

    public static  String ACCESS_TOKEN="";
    /**
     * 获取Access_Token接口
     */
    public static String  GET_ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 自定义菜单 创建接口
     */
    public static String SET_MENU_CREATE="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    /**
     * 设置所属行业
     */
    public static String SET_INDUSTRY="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=";
    /**
     *获得模板ID
     */
    public static final String GET_TEMPLATE_ID ="https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=";
    /**
     * 获取模板列表
     */
    public static final String GET_ALL_TEMPLATE ="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=";
    /**
     * 删除模板
     */
    public static final String DEL_TEMPLATE ="https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=";
    /**
     * 发送模板消息
     */
    public static final String SEND_TEMPLATE_MSG ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" ;
}
