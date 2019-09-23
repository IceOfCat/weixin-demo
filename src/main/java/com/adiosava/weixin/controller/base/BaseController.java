package com.adiosava.weixin.controller.base;


import com.adiosava.weixin.common.exception.AlertException;
import com.adiosava.weixin.common.exception.InvalidParamException;
import com.adiosava.weixin.util.json.JsonUtil;
import com.adiosava.weixin.util.json.Response;
import com.adiosava.weixin.util.lang.StringUtil;
import com.adiosava.weixin.util.tool.ToolDateTime;
import com.adiosava.weixin.util.tool.ToolString;
import com.adiosava.weixin.util.tool.ToolWeb;
import com.alibaba.fastjson.JSONException;
import lombok.extern.slf4j.Slf4j;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class BaseController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;



    protected final Response success() {
        Response success = Response.success();
        request.setAttribute("responseOk", success);
        return success;
    }

    protected final String getParameter(String name) {

        return request.getParameter(name);
    }

    protected final String getDownloadPath() {
        return  request.getServletContext().getRealPath("download");
    }

    protected final String getUrl() {
        return  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

    /**
     * 获取请求url
     *
     * @return
     */
    public static StringBuilder requestUrl(HttpServletRequest request) {
        try {
            Map<String, String[]> paraMap = request.getParameterMap();
            StringBuilder sb = new StringBuilder();
            sb.append("Request url:");
            sb.append(request.getRequestURI());
            sb.append("?");
            boolean isFirst = true;

            Iterator<Map.Entry<String, String[]>> it = paraMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String[]> entry = it.next();
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append("&");
                }
                sb.append(entry.getKey());
                if (entry.getValue() != null && StringUtils.isNotEmpty(entry.getValue()[0])) {
                    sb.append("=");
                    sb.append(entry.getValue()[0]);
                }
            }

            sb.append("\n");
            while (it.hasNext()) {
                Map.Entry<String, String[]> entry = it.next();
                sb.append("\t");
                sb.append(entry.getKey());
                sb.append("\t\t");
                if (entry.getValue() != null && StringUtils.isNotEmpty(entry.getValue()[0])) {
                    sb.append("=");
                    sb.append(entry.getValue()[0]);
                }
                sb.append("\n");
            }
            return sb;

        } catch (Exception e) {
            log.error("获取requestUrl失败", e);
            return new StringBuilder();
        }
    }

    /**
     * 请求body里是否包含name
     *
     * @param name
     * @return
     */
    protected final boolean contains(String name) {
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String nextElement = params.nextElement();
            if (nextElement.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 请求body里是否不包含name
     *
     * @param name
     * @return
     */
    protected final boolean notContains(String name) {
        return !contains(name);
    }

    /**
     * 获取str
     *
     * @param name
     * @return
     */
    protected final String getStr(String name) {
        String value = getParameter(name);
        if (value == null) {
            throw new InvalidParamException().isNull(name);
        }
        return value;
    }
    protected final String getStr(Map map, String name) {
        String value = (String) map.get(name);
        if (value == null) {
            throw new InvalidParamException().isNull(name);
        }
        return value;
    }


    /**
     * 获取String入参
     *
     * @param name 参数名
     * @param defaultValue 默认值
     * @return
     */
    protected final String getStr( String name, String defaultValue) {
        try {
            String result = getParameter(name);
            if (StringUtils.isBlank(result)) {
                return defaultValue;
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            return defaultValue;
        }
    }

    protected final String getStr( Map map,String name, String defaultValue) {
        try {
            String result = (String) map.get(name);
            if (StringUtils.isBlank(result)) {
                return defaultValue;
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            return defaultValue;
        }
    }

    /**
     * 获取String入参
     *
     * @param name
     *            参数名
     * @param defaultValue
     *            默认值
     * @return
     */
    protected final String getNotNullStr(String name, String defaultValue) {
        try {
            String result = getParameter(name);
            if (result == null) {
                return defaultValue;
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            return defaultValue;
        }
    }

    /**
     * 获取列表
     *
     * @param name
     * @param clazz
     *            适合String.class,Integer.class以及简单java bean对象
     * @return
     */
    protected <E> List<E> getList(String name, Class<E> clazz) {
        return JsonUtil.toList(getStr(name), clazz);
    }

    /**
     * 获取列表
     *
     * @param name
     * @param clazz
     *            适合String.class,Integer.class以及简单java bean对象
     * @return
     */
    protected <E> List<E> getList(String name, Class<E> clazz, List<E> defaultValue) {
        if (StringUtils.isEmpty(getParameter(name))) {
            return defaultValue;
        } else {
            return JsonUtil.toList(getParameter(name), clazz);
        }
    }

    /**
     * 按','分隔开，返回list
     *
     * @param name
     * @return
     */
    protected List<String> getStrList(String name) {
        String value = getStr(name);
        if (StringUtils.isBlank(value)) {
            return new LinkedList<>();
        }
        return   Arrays.asList( value.split(","));
    }


    /**
     * 按','分隔开，返回list
     *
     * @param name
     * @param defaultValue
     * @return
     */
    protected List<String> getStrList(String name, List<String> defaultValue) {
        String value = getStr(name, "");
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return   Arrays.asList( value.split(","));
    }

    /**
     * 按','分隔开，返回list
     *
     * @param name
     * @return
     */
    protected List<Integer> getIntList(String name) {
        String value = getStr(name, null);
        if (StringUtils.isBlank(value)) {
            return new LinkedList<>();
        }
        String[] split = value.split(",");
        List<Integer> result = new LinkedList<>();
        for (String str : split) {
            result.add(Integer.parseInt(str));
        }
        return result;
    }

    /**
     * 返回long
     *
     * @param name
     * @return
     */
    protected Long getLong(String name) {
        String temp = getParameter(name);
        if (StringUtils.isBlank(temp)) {
            throw new InvalidParamException().isNull(name);
        }
        try {
            return Long.parseLong(temp);
        } catch (NumberFormatException e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }

    /**
     * 返回long
     *
     * @param name
     * @return
     */
    protected Long getLong(Map map,String name) {
        String temp = (String) map.get(name);
        if (StringUtils.isBlank(temp)) {
            throw new InvalidParamException().isNull(name);
        }
        try {
            return Long.parseLong(temp);
        } catch (NumberFormatException e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }

    /**
     * 返回long
     *
     * @param name
     * @param defaultValue
     *            默认值
     * @return
     */
    protected Long getLong(String name, Long defaultValue) {
        String temp = getParameter(name);
        if (StringUtils.isBlank(temp)) {
            return defaultValue;
        }
        try {
            return Long.parseLong(temp);
        } catch (NumberFormatException e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }
    protected Long getLong(Map map,String name, Long defaultValue) {
        String temp = (String) map.get(name);
        if (StringUtils.isBlank(temp)) {
            return defaultValue;
        }
        try {
            return Long.parseLong(temp);
        } catch (NumberFormatException e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }

    /**
     * 返回int的请求参数
     *
     * @param name
     * @return
     */
    protected final Integer getInt(String name) {
        String value = getParameter(name);
        if (StringUtils.isBlank(value)) {
            throw new InvalidParamException().isNull(name);
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }
    protected final Integer getInt(Map map,String name) {
        String value = (String) map.get(name);
        if (StringUtils.isBlank(value)) {
            throw new InvalidParamException().isNull(name);
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }

    /**
     * 获取公共的参数值
     * @param name
     * @return
     */
    protected final Integer getCommonValue(String name) {
        if (request.getAttribute(name) == null || StringUtils.isEmpty(String.valueOf(request.getAttribute(name)))) {
            String value = getParameter(name) != null? getParameter(name) : request.getHeader(name);
            if (StringUtils.isBlank(value)) {
                throw new InvalidParamException().isNull(name);
            }
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
            }
        } else {
            return Integer.valueOf(String.valueOf(request.getAttribute(name)));
        }
    }

    /**
     * 返回int
     *
     * @param name
     * @param defaultValue
     *            默认值
     * @return
     */
    protected final Integer getInt(String name, Integer defaultValue) {
        String resultStr = getParameter(name);
        if (StringUtils.isBlank(resultStr)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(resultStr);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    protected final Integer getInt(Map map,String name, Integer defaultValue) {
        Integer result = (Integer) map.get(name);
        if (result==null) {
            return defaultValue;
        }
        try {
            return result;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 返回short
     *
     * @param name
     * @return
     */
    protected Short getShort(String name) {
        String temp = getParameter(name);
        if (StringUtils.isBlank(temp)) {
            throw new InvalidParamException().isNull(name);
        }
        try {
            return new Short(temp);
        } catch (NumberFormatException e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }

    /**
     * 返回short
     *
     * @param name
     * @param defaultValue
     *            默认值
     * @return
     */
    protected Short getShort(String name, Integer defaultValue) {
        String temp = getParameter(name);
        if (StringUtils.isBlank(temp)) {
            return defaultValue == null ? null : defaultValue.shortValue();
        }
        try {
            return new Short(temp);
        } catch (NumberFormatException e) {
            return defaultValue == null ? null : defaultValue.shortValue();
        }
    }

    /**
     * 返回BigDecimal
     *
     * @param name
     * @return
     */
    protected BigDecimal getDecimal(String name) {
        String str = getParameter(name);
        try {
            return new BigDecimal(str);
        } catch (Exception e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }

    /**
     * 返回BigDecimal
     *
     * @param name
     * @param defaultValue
     *            默认值
     * @return
     */
    protected BigDecimal getDecimal(String name, BigDecimal defaultValue) {
        String str = getParameter(name);
        if (str == null) {
            return defaultValue;
        }
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 返回boolean
     *
     * @param name
     * @return
     */
    protected Boolean getBoolean(String name) {
        String str = getStr(name);
        if ("1".equals(str) || "true".equals(str)) {
            return Boolean.TRUE;
        }
        if ("0".equals(str) || "false".equals(str)) {
            return Boolean.FALSE;
        }
        throw new InvalidParamException().setMessage(StringUtil.format("值{}不是合法的Boolean值", name));
    }
    protected Boolean getBoolean(Map map,String name) {
        String str = (String) map.get(name);
        if ("1".equals(str) || "true".equals(str)) {
            return Boolean.TRUE;
        }
        if ("0".equals(str) || "false".equals(str)) {
            return Boolean.FALSE;
        }
        throw new InvalidParamException().setMessage(StringUtil.format("值{}不是合法的Boolean值", name));
    }

    /**
     * 返回boolean
     *
     * @param name
     * @param defaultValue
     *            默认值
     * @return
     */
    protected Boolean getBoolean(String name, Boolean defaultValue) {
        String str = getStr(name, "");
        if ("1".equals(str) || "true".equals(str)) {
            return Boolean.TRUE;
        }
        if ("0".equals(str) || "false".equals(str)) {
            return Boolean.FALSE;
        }
        return defaultValue;
    }
    protected Boolean getBoolean(Map map,String name, Boolean defaultValue) {
        String str = (String) map.get(name);
        if ("1".equals(str) || "true".equals(str)) {
            return Boolean.TRUE;
        }
        if ("0".equals(str) || "false".equals(str)) {
            return Boolean.FALSE;
        }
        return defaultValue;
    }

    /**
     * 返回pageNo
     *
     * @return
     */
    private int getPageNo() {
        return getInt("pageNo", 1) < 1 ? 1 : getInt("pageNo", 1);
    }

    private int getPageNo(Map map) {
        return getInt(map,"pageNo", 1) < 1 ? 1 : getInt(map,"pageNo", 1);
    }

    /**
     * 返回pageSize
     *
     * @return
     */
    private int getPageSize() {
        return getInt("pageSize", 10) < 1 ? 10 : getInt("pageSize", 10);
    }

    private int getPageSize(Map map) {

        return getInt(map,"pageSize", 10) < 1 ? 10 : getInt(map,"pageSize", 10);
    }

    /**
     * 被排序的字段
     *
     * @return
     */
    protected String getOrderColunm() {
        String orderColumn = getStr("orderColunm", null) == null ? getStr("orderColumn", null) : getStr("orderColunm", null);
        log.debug("分页=>排序条件：orderColunm = :{}", orderColumn);
        return orderColumn;
    }

    /**
     * 排序模式
     *
     * @return
     */
    protected String getOrderMode() {
        String orderMode = getStr("orderMode", null);
        log.debug("分页=>排序方式：orderMode = :{}", orderMode);
        return orderMode;
    }


    /**
     * 获取查询对象
     *
     * @return
     */
    protected Map<String, Object> getQueryParam() {
        Map<String, Object> queryParam = new HashMap<>();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            String paraValue = getStr(paraName);
            if ( StringUtils.isNotEmpty(paraName)) {
                String key = paraName;
                if (ToolString.regExpVali(key, ToolString.regExp_letter_5)) {
                    queryParam.put(key, paraValue.trim());
                }
            }
        }
        return queryParam;
    }

    /**
     * 获取周期
     *
     * @param dateStr
     * @param attachStr
     * @return
     */
    protected final Date getActivityDate(String dateStr, String attachStr) {
        if (StringUtils.isEmpty(dateStr) || "null".equals(dateStr)) {
            throw new AlertException().setAlertMessage("日期不能为空");
        }
        SimpleDateFormat format = new SimpleDateFormat(ToolDateTime.pattern_ymd_hm);
        try {
            return format.parse(dateStr + ":00");
        } catch (ParseException e) {
            try {
                return format.parse(dateStr + " " + attachStr);
            } catch (ParseException e1) {
                log.error("getActivityDate异常：date值" + dateStr + "，attachStr值" + attachStr);
                throw new AlertException().setAlertMessage("日期格式不正确");
            }
        }
    }

    /**
     * 获取属性值
     *
     * @param name
     * @return
     */
    protected final String getAttribute(String name) {
        return String.valueOf(request.getAttribute(name));
    }

    /**
     * 重写getPara，进行二次decode解码
     *
     */
    public String getPara(String name) {
        String value = request.getParameter(name);
        handValue(name, value);
        return value;
    }
    private static void handValue(String name, String value) {
        if (value == null) {
            log.warn("in param ,name:" + name + " value is null .");
        }
    }

    protected final int getStart() {
        return getInt("pageNumber", 0) * getInt("pageSize");
    }

    protected final int getLen() {
        return getInt("pageSize", 10);
    }

    /**
     * 获取cookie中的值
     * @param cookiName
     * @return
     */
    protected final String getCookieValue(String cookiName){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookiName)) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }
    /**
     * 获取cookie中的值
     * @param cookiName
     * @param defaultValue 默认值
     * @return
     */
    protected final String getCookieValue(String cookiName,String defaultValue){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookiName)) {
                    return cookie.getValue();
                }
            }
        }
        return defaultValue;
    }

    protected final int getCookieInt(String cookieName){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    if (StringUtils.isBlank(cookie.getValue())) {
                        return 0;
                    }
                    return Integer.valueOf(cookie.getValue());
                }
            }
        }
        return 0;
    }

    protected final JSONObject getJson(String name) {
        String value = getParameter(name);
        if (value == null) {
            throw new InvalidParamException().isNull(name);
        }
        try {
            return JSONObject.fromObject(name);
        } catch (JSONException e) {
            throw new InvalidParamException().setMessage(StringUtil.format("参数{}不是合法的数字", name));
        }
    }

    protected final JSONArray getJson(String name, JSONArray defaultValue) {
        String value = getParameter(name);
        if (value == null) {
            return defaultValue;
        }
        try {
            return JSONArray.fromObject(name);
        } catch (JSONException e) {

            return defaultValue;
        }
    }
    /**
     * 获取请求IP地址
     *
     * @return
     */
    protected final String getIpAddress() {
        String spbillCreateIp = ToolWeb.getIpAddr(request);
        if (StringUtils.isBlank(spbillCreateIp)) {
            spbillCreateIp = "127.0.0.1";
        }
        return spbillCreateIp;
    }





}