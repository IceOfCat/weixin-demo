package com.adiosava.weixin.util.lang;

import com.adiosava.weixin.util.tool.ToolDateTime;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    private Map<String, Object> param=new HashMap<>();
    public static MapUtil builder() {
        return new MapUtil();
    }
    public MapUtil put(String key, Object value) {
        this.param.put(key, value);
        return this;
    }
    public Map<String, Object> build() {
        return this.param;
    }
    public Map<String, String> buildString() {
        Map<String,String> map = new HashMap<>();
        for(Map.Entry<String,Object> entry:param.entrySet()){
            map.put(entry.getKey(), entry.getValue().toString());
        }
        return map;
    }
    public Map<String, Object> buildObject() {
        Map<String,Object> map = new HashMap<>();
        for(Map.Entry<String,Object> entry:param.entrySet()){
            map.put(entry.getKey(), entry.getValue().toString());
        }
        return map;
    }

    public static Date getDate(String dateSource , Map map){
        SimpleDateFormat dateFormat = new SimpleDateFormat(ToolDateTime.pattern_ymd_hms);
        Date date = null;
        try {
            String value = (String) map.get(dateSource);
            if(StringUtils.isEmpty(value)){
                return null;
            }
            date = dateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
