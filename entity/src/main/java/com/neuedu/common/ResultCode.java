package com.neuedu.common;

import lombok.Getter;

/**
 * @author 施子安
 * @create
 */
@Getter
public enum ResultCode {
    //成功
    SUCCESS(200),
    //失败
    FAILED(500),
    //未认证
    UNAUTH(401),
    //没有权限
    FORBID(403);
    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
