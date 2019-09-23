package com.adiosava.weixin.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.ejb.Startup;
import java.util.List;

/**
 * @Author yuxudong
 * @Date 2019/9/21 15:33
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Startup
public class WechatMenuDTO {

    /**
     * 一级菜单数组，个数应为1~3个
     */
    private List<WechatButtonDTO> button;

}
