package com.adiosava.weixin.util.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@JsonSerialize(using=JsonMapSerializer.class)
public final class JsonMap {
	private Map<String, Object> map = new LinkedHashMap<>();

	public  Map<String, Object> getMap() {
		return map;
	}

	public static JsonMap build() {
		return new JsonMap();
	}

	private JsonMap() {

	}
	public boolean contains(String key) {
		return map.containsKey(key);
	}

	public JsonMap put(String key, String value) {
		this.map.put(key, value);
		return this;
	}

	public JsonMap putAll(JsonMap map) {
		this.map.putAll(map.map);
		return this;
	}

	public JsonMap put(String key, JsonMap value) {
		this.map.put(key, value);
		return this;
	}

	public JsonMap put(String key, int value) {
		this.map.put(key, value);
		return this;
	}

	public JsonMap put(String key, List<?> value) {
		this.map.put(key, value);
		return this;
	}

	public JsonMap put(String key, long value) {
		this.map.put(key, value);
		return this;

	}

	public JsonMap put(String key, boolean value) {
		this.map.put(key, value);
		return this;
	}



	public JsonMap put(String key, Object value) {
		this.map.put(key, value);
		return this;
	}

	public Object get(String key) {
		return this.map.get(key);
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(map);
	}
	
	
	
}
