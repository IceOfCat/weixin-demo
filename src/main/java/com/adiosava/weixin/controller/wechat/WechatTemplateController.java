package com.adiosava.weixin.controller.wechat;

import com.adiosava.weixin.controller.base.BaseController;
import com.adiosava.weixin.pojo.dto.WeChatRequestDTO;
import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.service.WechatTemplateService;
import com.adiosava.weixin.util.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yuxudong
 * @Date 2019/9/23 8:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/wechat/template")
public class WechatTemplateController  extends BaseController {

    @Autowired
    private WechatTemplateService wechatTemplateService;

    /**
     * 参数
     * industry_id1 公众号模板消息所属行业编号
     * industry_id2 公众号模板消息所属行业编号
     * 设置所属行业
     * @return
     */
    @RequestMapping("/setIndustry")
    public Response setIndustry(@RequestBody WeChatRequestDTO requestDTO){
        WeChatResponseDTO  responseDTO=wechatTemplateService.setIndustry(requestDTO);
        return success().put("result", responseDTO);
    }

    /**
     * 获取模板id
     * 参数 template_id_short
     * @param requestDTO
     * @return
     */
    @RequestMapping("/getTemplateId")
    public Response getTemplateId(@RequestBody WeChatRequestDTO requestDTO){
        WeChatResponseDTO  responseDTO= wechatTemplateService.getTemplateId(requestDTO);
        return success().put("result", responseDTO);
    }

    /**
     * 获取模板列表
     * @return
     */
    @RequestMapping("/getAllTemplat")
    public Response getAllTemplat(){
        WeChatResponseDTO  responseDTO= wechatTemplateService.getAllTemplat();
        return success().put("result", responseDTO);
    }

    /**
     * 删除模板
     * 参数 template_id
     * @return
     */
    @RequestMapping("/delTemplate")
    public Response delTemplate(@RequestBody WeChatRequestDTO requestDTO){
        WeChatResponseDTO  responseDTO= wechatTemplateService.delTemplate(requestDTO);
        return success().put("result", responseDTO);
    }

}
