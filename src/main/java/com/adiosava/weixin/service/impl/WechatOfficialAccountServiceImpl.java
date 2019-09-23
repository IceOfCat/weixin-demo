package com.adiosava.weixin.service.impl;

import com.adiosava.weixin.etc.Profile;
import com.adiosava.weixin.pojo.dto.WeChatRequestDTO;
import com.adiosava.weixin.pojo.dto.WeChatResponseDTO;
import com.adiosava.weixin.qq.weixin.WechatParam;
import com.adiosava.weixin.service.WeChatOfficialAccountService;
import com.adiosava.weixin.util.json.JsonMap;
import com.adiosava.weixin.util.web.WechatHttpClient;
import com.adiosava.weixin.util.web.WechatUrl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信公众号接口实现
 * @Author yuxudong
 * @Date 2019/9/20 15:59
 * @Version 1.0
 **/
@Slf4j
@Service
public class WechatOfficialAccountServiceImpl implements WeChatOfficialAccountService {

    /**
     * 校验请求的合法性
     * @param weChatRequestDTO
     * @return
     */
    @Override
    public Boolean checkSignature(WeChatRequestDTO weChatRequestDTO) {
        String timestamp = weChatRequestDTO.getTimestamp();
        String nonce = weChatRequestDTO.getNonce();
        // 对token、timestamp、和nonce按字典排序.
        String[] paramArr = new String[] {Profile.WECHAT_OFFICIAL_ACCOUNT_TOKEN, timestamp, nonce};
        Arrays.sort(paramArr);
        // 将排序后的结果拼接成一个字符串.
        String content  = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
        String ciphertext = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行sha1加密.
            byte[] digest = md.digest(content.getBytes());
            ciphertext = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        // 将sha1加密后的字符串与signature进行对比.
        return ciphertext != null && ciphertext.equals(weChatRequestDTO.getSignature().toUpperCase());
    }

    @Override
    public WeChatResponseDTO getAccessToken() {
        JsonMap jsonMap = JsonMap.build().put(WechatParam.GRANT_TYPE, "client_credential")
                                         .put(WechatParam.APPID, Profile.WECHAT_OFFICIAL_ACCOUNT_TEST_APP_ID)
                                         .put(WechatParam.SECRET, Profile.WECHAT_OFFICIAL_ACCOUNT_TEST_APP_SECRET);
        WeChatResponseDTO responseDTO = WechatHttpClient.sendGet(WechatUrl.GET_ACCESS_TOKEN, jsonMap.getMap());

        if(null!=responseDTO && StringUtils.isNotEmpty(responseDTO.getAccess_token())){
            WechatUrl.ACCESS_TOKEN=responseDTO.getAccess_token();
        }

        WechatUrl.ACCESS_TOKEN=responseDTO.getAccess_token();
        return responseDTO;
    }

    /**
     * 将字节数组转换为十六进制字符串.
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串.
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1' , '2', '3', '4' , '5', '6', '7' , '8', '9', 'A' , 'B', 'C', 'D' , 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
}
