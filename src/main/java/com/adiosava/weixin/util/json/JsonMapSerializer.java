package com.adiosava.weixin.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonMapSerializer extends JsonSerializer<JsonMap> {

	@Override
	public void serialize(JsonMap value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(gen, value.getMap());
	}

}