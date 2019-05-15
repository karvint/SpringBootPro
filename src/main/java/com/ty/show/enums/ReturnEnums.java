package com.ty.show.enums;

import lombok.Getter;

/**
 * 返回值枚举类
 */
@Getter
public enum ReturnEnums {

    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    MENU_NOT_FOUND(100001,"菜单未找到"),
    LOGIN_ERROR(100002,"JWT校验失败"),
    LOGIN_INFO_ERROR(100003,"用户名密码错误"),
    SQL_RUN_ERROR(100004,"SQL执行错误"),
    USERNAME_NOT_FOUND(100005,"用户名不存在"),
    ;
    private Integer code;
    private String msg;
    ReturnEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
