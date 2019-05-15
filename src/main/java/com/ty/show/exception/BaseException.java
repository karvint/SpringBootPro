package com.ty.show.exception;

import com.ty.show.enums.FileEnums;
import com.ty.show.enums.ReturnEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础异常捕获
 * 目的是保证前后台流畅运行，前台能够收到指定的数据类型
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(ReturnEnums returnEnums) {
        super(returnEnums.getMsg());
        this.code = returnEnums.getCode();
    }

    public BaseException(FileEnums fileEnums){
        super(fileEnums.getMsg());
        this.code = fileEnums.getCode();
    }
}
