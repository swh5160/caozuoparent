package com.neuedu.common;

import lombok.Getter;

/**
 * @author 施子安
 * @create
 */
@Getter
public class ResultJson {
    private Integer code;
    private Object content;
    private String message;

    private ResultJson(Integer code, Object content, String message) {
        this.code = code;
        this.content = content;
        this.message = message;
    }
    public static ResultJson getInstance(ResultCode resultCode, Object content, String message){
        return new ResultJson(resultCode.getCode(),content,message);
    }
    //成功
    public static ResultJson success(Object content, String message) {
        return getInstance(ResultCode.SUCCESS,content,message);
    }
    //没有消息等成功
    public static ResultJson success(Object content){
        return success(content,null);
    }
    //失败
    public static ResultJson failed(Object content, String message){
        return getInstance(ResultCode.FAILED,content,message);
    }
    //没有消息的失败
    public static ResultJson failed(String message){
        return failed(null,message);
    }
}
