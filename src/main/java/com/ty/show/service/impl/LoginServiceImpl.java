package com.ty.show.service.impl;

import com.ty.show.dataobject.entity.User;
import com.ty.show.dataobject.mapper.LoginMapper;
import com.ty.show.enums.ReturnEnums;
import com.ty.show.exception.BaseException;
import com.ty.show.service.LoginService;
import com.ty.show.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: cxm
 * @date: 2019/5/9
 * @description:
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public User regsiter(User user) {
        try {
            Integer res = loginMapper.regsiter(user);
            User param = new User();
            param.setUsername(user.getUsername());
            return loginMapper.getUserListOne(param);
        } catch (Exception e) {
            log.error("注册失败：【{}】",e.getStackTrace());
            throw new BaseException(ReturnEnums.SQL_RUN_ERROR);
        }
    }

    @Override
    public Boolean checkUsername(String username) {
        try {
            Integer res = loginMapper.checkUsername(username);
            if(res==0){// 如果username不存在
                return true;
            }else{// 如果username已存在
                return false;
            }
        } catch (Exception e) {
            log.error("检查username异常：【{}】",e.getStackTrace());
            throw new BaseException(ReturnEnums.SQL_RUN_ERROR);
        }
    }

    @Override
    public User login(User user) {
        User res = loginMapper.login(user);
        if(res==null){
            // 用户名不存在
            throw new BaseException(ReturnEnums.USERNAME_NOT_FOUND);
        }else if(user.getPassword().equals(res.getPassword())){
            // 用户名和密码匹配 返回结果为成功
            res.setPassword(null);
            return res;
        }else{
            // 用户名密码错误
            throw new BaseException(ReturnEnums.LOGIN_INFO_ERROR);
        }
    }

    @Override
    public List<UserVo> getUserList(User user) {
        try {
            return loginMapper.getUserList(user);
        } catch (Exception e) {
            log.error("查询用户列表失败：【{}】",e.getStackTrace());
            throw new BaseException(ReturnEnums.SQL_RUN_ERROR);
        }
    }

    @Override
    public Integer resetPwd(String id) {
        try {
            return loginMapper.resetPwd(id);
        } catch (Exception e) {
            log.error("重置用户密码失败：【{}】",e.getStackTrace());
            throw new BaseException(ReturnEnums.SQL_RUN_ERROR);
        }
    }

    @Override
    public Integer delete(String id) {
        return loginMapper.delete(id);
    }
}
