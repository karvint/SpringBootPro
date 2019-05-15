package com.ty.show.controller;

import com.ty.show.dataobject.ResData;
import com.ty.show.dataobject.entity.Want;
import com.ty.show.enums.ReturnEnums;
import com.ty.show.service.WantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: tty
 * @date: 2019/5/9
 * @description:
 */
@RequestMapping(value = "/want")
@RestController
public class WantController {

    @Autowired
    WantService wantService;


    /**
     * 根据条件查找求职意向
     * @param want
     * @return
     */
    @RequestMapping(value = "/info")
    public ResData getWantInfo(@RequestBody Want want){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        List<Want> res = wantService.getWantInfo(want);
        resData.setRows(res);
        return resData;
    }

    /**
     * 注册时填写求职意向
     * @param want
     * @return
     */
    @RequestMapping(value = "/insert")
    public ResData insertWant(@RequestBody Want want){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        Integer res =  wantService.insertWant(want);
        resData.setData(res);
        return resData;
    }
}
