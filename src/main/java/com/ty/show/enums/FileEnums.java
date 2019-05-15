package com.ty.show.enums;

import lombok.Getter;

@Getter
public enum FileEnums {

    FILE_EXIST_ERROR(110001,"该路径下文件已存在"),
    FILE_INPUT_ERROR(110002,"文件读取异常"),
    FILE_OUTPUT_ERROR(110003,"文件存储异常"),
    FILE_PATH_NOT_FOUND_ERROR(110004,"文件路径未找到"),
    FILE_CREATE_ERROR(110005,"文件创建失败"),
    FILE_UPLOAD_ERROR(110006,"文件上传失败"),
    FILE_DOWNLOAD_ERROR(110007,"文件下载失败"),
    FILE_IO_STREAM_CLOSE_ERROR(110008,"文件流关闭失败"),
    SET_FILE_CHARSET_ERROR(110009,"设置字符集失败"),
    FILE_DOWNLOAD_TOTAL_ERROR(110010,"下载文件失败"),
    CHECK_FILE_URL_ERROR(40007,"检查上传文件地址异常"),
    ;
    private Integer code;
    private String msg;
    FileEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
