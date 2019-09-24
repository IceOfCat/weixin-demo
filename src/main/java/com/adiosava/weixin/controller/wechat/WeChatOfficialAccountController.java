package com.adiosava.weixin.controller.wechat;

import com.adiosava.weixin.controller.base.BaseController;
import com.adiosava.weixin.pojo.dto.WeChatRequestDTO;
import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.service.WeChatOfficialAccountService;
import com.adiosava.weixin.service.WechatEventService;
import com.adiosava.weixin.util.json.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信小程序公众号接口
 * @Author yuxudong
 * @Date 2019/9/20 14:45
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WeChatOfficialAccountController extends BaseController {

    @Autowired
    private WeChatOfficialAccountService weChatOfficialAccountService;
    @Autowired
    private WechatEventService wechatEventService;

    /**
     * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
     * 接收公众平台消息推送get请求，会带如下四个参数
     * signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp 时间戳
     * nonce 随机数
     * echostr 随机字符串
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET})
    @ResponseBody
    public void    receive( WeChatRequestDTO weChatRequestDTO){
        log.info("接收到微信消息");
        Boolean checkSignature =weChatOfficialAccountService.checkSignature(weChatRequestDTO);
        if(checkSignature){
            log.info("验证成功");
            try {
                PrintWriter writer = response.getWriter();
                writer.write(weChatRequestDTO.getEchostr());
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseBody
    public void    processEvent(){
        log.info("接收到微信消息");
        try {
            boolean b = wechatEventService.processEvent(request);
            PrintWriter writer = response.getWriter();
            if(b){
                writer.write("success");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @RequestMapping("/getToken")
    @ResponseBody
    public Response getAccessToken(){
        WeChatResponseDTO responseDTO = weChatOfficialAccountService.getAccessToken();
        return success().put("result", responseDTO);
    }

}
