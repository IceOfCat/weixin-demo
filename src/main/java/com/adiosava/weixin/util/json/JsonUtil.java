package com.adiosava.weixin.util.json;

import com.adiosava.weixin.common.exception.MineIllegalStateException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.ListOrderedMap;

import java.io.IOException;
import java.util.*;

@Slf4j
public class JsonUtil {
	public static ObjectNode createObject() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.createObjectNode();
	}

	public static ArrayNode createArray() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.createArrayNode();
	}

	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		ObjectMapper mapper = new ObjectMapper();
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	public static <E> List<E> toList(String text, Class<E> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			JavaType javaType = getCollectionType(ArrayList.class, clazz);
			return mapper.readValue(text, javaType);
		} catch (IOException e) {
			throw new MineIllegalStateException(e);
		}
	}

	/**
	 * 将json转化成map
	 *
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> convertJsonStrToMap(String jsonStr) {

		Map<String, Object> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {
		});

		return map;
	}

	public static Map toMap(String str) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			Map map = mapper.readValue(str, Map.class);
			Iterator<?> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				Object key = iterator.next();
				log.debug(key + ":");
				log.debug(map.get(key).toString());
			}
			return map;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

	/**
	 * json转换map.
	 * <br>详细说明
	 *
	 * @param jsonStr json字符串
	 * @return Map<String   ,   Object> 集合
	 * @throws
	 * @author slj
	 */
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		ListOrderedMap map = new ListOrderedMap();
		//最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			//如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	@Getter
	@Setter
	@ToString
	private static class Temp {
		private int id = 1;
	}

	public static String toFormatString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new MineIllegalStateException(e);
		}
	}

	public static String toString(Object object) {
		ObjectMapper m = new ObjectMapper();
		try {
			return m.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("write error ,object is:" + object, e);
			return "";
		}
	}

	@SuppressWarnings("unchecked")
	public static List<LinkedHashMap<String, Object>> toComplexList(String text) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(text, List.class);
		} catch (IOException e) {
			log.error("can not read text", e);
		}
		return new LinkedList<>();
	}

	@SuppressWarnings("rawtypes")
	public static List toList(String text) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(text, List.class);
		} catch (IOException e) {
			log.error("can not read text", e);
		}
		return new LinkedList<>();
	}

	public static String getJSonString(Object object){
		ObjectMapper objectMapper=new ObjectMapper();
		String s="";
		try {
			s = objectMapper.writeValueAsString(object);
		}catch (Exception e){
			e.printStackTrace();
		}
		return s;
	}

    public static void println(Object object){
	    System.out.println(getJSonString(object));
    }
}
