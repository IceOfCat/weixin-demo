package com.adiosava.weixin.pojo.dto;

import lombok.*;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yuxudong
 * @Date 2019/9/23 16:56
 * @Version 1.0
 **/
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WechatEventMessageDTO {
    @XmlElement(
            name = "ToUserName"
    )
    private String toUserName;
    @XmlElement(
            name = "FromUserName"
    )
    private String fromUserName;
    @XmlElement(
            name = "CreateTime"
    )
    private Integer createTime;
    @XmlElement(
            name = "MsgType"
    )
    private String msgType;
    @XmlElement(
            name = "Event"
    )
    private String event;
    @XmlElement(
            name = "EventKey"
    )
    private String eventKey;
    @XmlElements({@XmlElement(
            name = "MsgId"
    ), @XmlElement(
            name = "MsgID"
    )})
    private String msgId;
    @XmlElement(
            name = "Content"
    )
    private String content;
    @XmlElement(
            name = "PicUrl"
    )
    private String picUrl;
    @XmlElement(
            name = "MediaId"
    )
    private String mediaId;
    @XmlElement(
            name = "Format"
    )
    private String format;
    @XmlElement(
            name = "Recognition"
    )
    private String recognition;
    @XmlElement(
            name = "ThumbMediaId"
    )
    private String thumbMediaId;
    @XmlElement(
            name = "Location_X"
    )
    private String location_X;
    @XmlElement(
            name = "Location_Y"
    )
    private String location_Y;
    @XmlElement(
            name = "Scale"
    )
    private String scale;
    @XmlElement(
            name = "Label"
    )
    private String label;
    @XmlElement(
            name = "Title"
    )
    private String title;
    @XmlElement(
            name = "Description"
    )
    private String description;
    @XmlElement(
            name = "Url"
    )
    private String url;
    @XmlElement(
            name = "Ticket"
    )
    private String ticket;
    @XmlElement(
            name = "Latitude"
    )
    private String latitude;
    @XmlElement(
            name = "Longitude"
    )
    private String longitude;
    @XmlElement(
            name = "Precision"
    )
    private String precision;
    @XmlElement(
            name = "Status"
    )
    private String status;
    @XmlElement(
            name = "TotalCount"
    )
    private Integer totalCount;
    @XmlElement(
            name = "FilterCount"
    )
    private Integer filterCount;
    @XmlElement(
            name = "SentCount"
    )
    private Integer sentCount;
    @XmlElement(
            name = "ErrorCount"
    )
    private Integer errorCount;
    @XmlElement(
            name = "CopyrightCheckResult"
    )

    private Integer expiredTime;
    @XmlElement(
            name = "FailTime"
    )
    private Integer failTime;
    @XmlElement(
            name = "FailReason"
    )
    private String failReason;
    @XmlElement(
            name = "UniqId"
    )
    private String uniqId;
    @XmlElement(
            name = "PoiId"
    )
    private String poiId;
    @XmlElement(
            name = "Result"
    )
    private String result;
    @XmlElement(
            name = "Msg"
    )
    private String msg;
    @XmlElement(
            name = "LotteryId"
    )
    private String lotteryId;
    @XmlElement(
            name = "Money"
    )
    private Integer money;
    @XmlElement(
            name = "BindTime"
    )
    private Integer bindTime;
    @XmlElement(
            name = "ConnectTime"
    )
    private Integer connectTime;
    @XmlElement(
            name = "ExpireTime"
    )
    private Integer expireTime;
    @XmlElement(
            name = "VendorId"
    )
    private String vendorId;
    @XmlElement(
            name = "ShopId"
    )
    private String shopId;
    @XmlElement(
            name = "DeviceNo"
    )
    private String deviceNo;
    @XmlElement(
            name = "KeyStandard"
    )
    private String keyStandard;
    @XmlElement(
            name = "KeyStr"
    )
    private String keyStr;
    @XmlElement(
            name = "Country"
    )
    private String country;
    @XmlElement(
            name = "Province"
    )
    private String province;
    @XmlElement(
            name = "City"
    )
    private String city;
    @XmlElement(
            name = "Sex"
    )
    private Integer sex;
    @XmlElement(
            name = "Scene"
    )
    private Integer scene;
    @XmlElement(
            name = "RegionCode"
    )
    private String regionCode;
    @XmlElement(
            name = "ReasonMsg"
    )
    private Integer reasonMsg;
    @XmlAnyElement
    private List<Element> otherElements;

    public WechatEventMessageDTO() {
    }

    public List<Element> getOtherElements() {
        return this.otherElements;
    }

    public void setOtherElements(List<Element> otherElements) {
        this.otherElements = otherElements;
    }

    public Map<String, String> otherElementsToMap() {
        Map<String, String> map = new LinkedHashMap();
        if (this.otherElements != null) {
            Iterator var2 = this.otherElements.iterator();

            while(var2.hasNext()) {
                Element e = (Element)var2.next();
                if (e.hasChildNodes() && e.getChildNodes().getLength() == 1 && e.getChildNodes().item(0).getNodeType() == 3) {
                    map.put(e.getTagName(), e.getTextContent());
                }
            }
        }

        return map;
    }
}
