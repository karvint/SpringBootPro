package com.ty.show.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ReturnEnums {

    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    MENU_NOT_FOUND(100001,"菜单未找到"),
    LOGIN_ERROR(100002,"JWT校验失败"),
    LOGIN_INFO_ERROR(100003,"用户名密码信息错误"),
    SQL_RUN_ERROR(100004,"SQL执行错误"),
    ;
    private Integer code;
    private String msg;
    ReturnEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
