package com.ty.show.dataobject.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private Integer id;
    private Integer type;// 1:学生 2：公司 3:管理员
    private String realname;
    private String address;
    private String tel;
    private String email;
    private String company;
    private Integer userId;
}
