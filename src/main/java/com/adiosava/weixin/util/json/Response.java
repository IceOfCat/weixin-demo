package com.adiosava.weixin.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Response {
    private int code;
    private String respCode;
    private List list;
    private Object data = JsonMap.build();

   private Response(int code) {
       this.code = code;
   }

    public static Response success() {
        Response result = new Response(0);
        return result;
    }

    public static Response error() {
        Response result = new Response(-1);
        return result;
    }

    private JsonMap jsonMap() {
        if (data == null) {
            data = JsonMap.build();
        }
        return (JsonMap) data;
    }

    public final boolean contains(String key) {
        return this.jsonMap().contains(key);
    }

    public Response put(String key, Object value) {
        this.jsonMap().put(key, value);
        return this;
    }

    public Response setList(List list){
        this.list=list;
        return this;
    }

    public Response setRespCode(String respCode){
        this.respCode=respCode;
        return this;
    }
    public Response setCode(int code){
        this.code=code;
        return this;
    }

    public Response setData(Object  data){
        this.data=data;
        return this;
    }



}
