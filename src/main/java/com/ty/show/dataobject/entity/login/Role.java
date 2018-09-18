package com.ty.show.dataobject.entity.login;

import lombok.Data;

@Data
public class Role {
    private String id;
    private String roleName;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
