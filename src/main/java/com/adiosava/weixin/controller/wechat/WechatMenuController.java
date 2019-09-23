package com.adiosava.weixin.controller.wechat;

import com.adiosava.weixin.controller.base.BaseController;
import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.pojo.dto.WechatMenuDTO;
import com.adiosava.weixin.service.WechatMenuService;
import com.adiosava.weixin.util.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yuxudong
 * @Date 2019/9/21 14:58
 * @Version 1.0
 **/

@RequestMapping("/wechat/menu")
@RestController
public class WechatMenuController extends BaseController {

    @Autowired
    private WechatMenuService wechatMenuService;

    /**
     * 创建自定义菜单
     * @return
     */
    @RequestMapping("/create")
    public Response create(@RequestBody WechatMenuDTO menuDTO){
        WeChatResponseDTO responseDTO = wechatMenuService.createMenu(menuDTO);
        return success().put("response", responseDTO);
    }


}
