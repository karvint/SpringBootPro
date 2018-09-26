package com.ty.show.dataobject.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Delete;

@Data
public class SqlData {
    private String sql;
    private String result;
    private String targetDatabase;
    private String method;
}
