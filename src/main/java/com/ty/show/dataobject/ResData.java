package com.ty.show.dataobject;

import com.ty.show.enums.ReturnEnums;
import lombok.Data;

import java.util.List;

@Data
public class ResData {
    private int code;
    private String msg;
    private Object Data;
    private List rows;
    private int total;

    public ResData(ReturnEnums returnEnums){
        this.code = returnEnums.getCode();
        this.msg = returnEnums.getMsg();
    }
    public ResData(){
    }
}
