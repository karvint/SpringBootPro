package com.ty.show.controller;

import com.ty.show.dataobject.ResData;
import com.ty.show.dataobject.entity.User;
import com.ty.show.enums.ReturnEnums;
import com.ty.show.service.LoginService;
import com.ty.show.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 控制层
 */
@RequestMapping(value = "/login")
@RestController// 里面的所有请求都是restful风格的 返回值都是一个对象
public class LoginController {


    @Autowired
    LoginService loginService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping(value = "/user")
    public ResData login(@RequestBody User user){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        User res = loginService.login(user);
        resData.setData(res);
        return resData;
    }

    /**
     * 用户注册方法
     * @param user
     * @return
     */
    @PostMapping(value = "/regsiter")
    public ResData regsiter(@RequestBody User user){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        User res = loginService.regsiter(user);
        resData.setData(res);
        return resData;
    }

    /**
     * 检查用户名是否重复
     * @param username
     * @return
     */
    @RequestMapping(value = "/checkUsername")
    public Boolean checkUsername(@RequestBody String username){
        Boolean res = loginService.checkUsername(username);
        return res;
    }

    /**
     * 条件查询用户信息列表
     * @param user
     * @return
     */
    @RequestMapping(value = "/list")
    public ResData getUserList(@RequestBody User user){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        List<UserVo> res = loginService.getUserList(user);
        resData.setRows(res);
        return resData;
    }

    /**
     * 重置用户名密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/resetPwd")
    public ResData resetPwd(String id){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        Integer res = loginService.resetPwd(id);
        resData.setData(res);
        return resData;
    }

    /**
     * 重置用户名密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public ResData delete(String id){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        Integer res = loginService.delete(id);
        resData.setData(res);
        return resData;
    }
}
