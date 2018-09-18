package com.ty.show.controller;

import com.ty.show.dataobject.ResData;
import com.ty.show.dataobject.entity.menu.Menu;
import com.ty.show.enums.ReturnEnums;
import com.ty.show.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/init")
    @ResponseBody
    public ResData menuInit(){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        List<Menu> menus = menuService.init();
        if(CollectionUtils.isEmpty(menus)){
            resData = new ResData(ReturnEnums.MENU_NOT_FOUND);
        }else{
            resData.setRows(menus);
        }
        return resData;
    }
}
