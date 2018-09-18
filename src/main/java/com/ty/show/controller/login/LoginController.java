package com.ty.show.controller.login;

import com.ty.show.dataobject.ResData;
import com.ty.show.dataobject.entity.jwt.Audience;
import com.ty.show.dataobject.entity.login.User;
import com.ty.show.enums.ReturnEnums;
import com.ty.show.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/login")
@RestController
public class LoginController {

    @Autowired
    Audience audience;

    @PostMapping(value = "/user")
    @ResponseBody
    public ResData login(@RequestBody User user){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        user.setId("10001");
        if(("admin".equals(user.getUserName())&&"123456".equals(user.getPassword()))||"10001".equals(user.getId())){
            resData.setMsg("Login Success!");
            String token = JwtHelper.createJWT(user.getUserName(),user.getId(),user.getRole().toString(),
                    audience.getClientId(),
                    audience.getName(),
                    audience.getExpiresSecond()*1000,
                    audience.getBase64Secret());
            user.setPassword("");
            user.setUserName("admin");
            user.setId("10001");
            user.setToken(token);
            resData.setData(user);
        }else{
            resData = new ResData(ReturnEnums.LOGIN_INFO_ERROR);
        }
        return resData;
    }

}
