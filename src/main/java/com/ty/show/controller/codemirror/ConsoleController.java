package com.ty.show.controller.codemirror;

import com.ty.show.dataobject.ResData;
import com.ty.show.enums.ReturnEnums;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/console")
public class ConsoleController {

    @PostMapping(value = "/get")
    public ResData getConsoleMsg(){
        ResData resData = new ResData(ReturnEnums.SUCCESS);

        return resData;
    }
}
