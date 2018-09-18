package com.ty.show.controller.jwt;

import com.ty.show.dataobject.ResData;
import com.ty.show.dataobject.entity.jwt.Audience;
import com.ty.show.dataobject.entity.login.User;
import com.ty.show.enums.ReturnEnums;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/securey")
@RestController
public class SecureyController {

    @Autowired
    Audience audience;

    @PostMapping(value = "/auto")
    @ResponseBody
    public ResData autoLogin(HttpServletRequest request){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        User user = new User();
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.get("userName").toString();
        String userid = claims.get("userId").toString();
        user.setUserName(userName);
        user.setId(userid);
        resData.setData(user);
        return resData;
    }
}
