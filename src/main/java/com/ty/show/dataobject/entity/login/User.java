package com.ty.show.dataobject.entity.login;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String password;
    private String id;
    private Role role;
    private String token;
}
