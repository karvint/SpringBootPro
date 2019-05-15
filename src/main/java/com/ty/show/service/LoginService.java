package com.ty.show.service;

import com.ty.show.dataobject.entity.User;
import com.ty.show.vo.UserVo;

import java.util.List;

public interface LoginService {
    User regsiter(User user);

    Boolean checkUsername(String username);

    User login(User user);

    List<UserVo> getUserList(User user);

    Integer resetPwd(String id);

    Integer delete(String id);
}
