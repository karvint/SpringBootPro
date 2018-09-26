package com.ty.show.controller.codemirror;

import com.ty.show.dataobject.ResData;
import com.ty.show.dataobject.entity.SqlData;
import com.ty.show.enums.ReturnEnums;
import com.ty.show.exception.BaseException;
import com.ty.show.service.codemirror.SqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/sql")
@RestController
@Slf4j
public class SqlController {

    @Autowired
    SqlService sqlService;

    @PostMapping(value = "run")
    @ResponseBody
    public ResData sqlRun(@RequestBody SqlData sqlData){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        try {
            String mType = sqlData.getSql().split(" ")[0].toUpperCase();
            switch (mType){
                case "SELECT":
                    resData.setData(sqlService.runSelect(sqlData));
                    break;
                case "UPDATE":
                    resData.setData(sqlService.runUpdate(sqlData));
                    break;
                case "INSERT":
                    resData.setData(sqlService.runInsert(sqlData));
                    break;
                case "DELETE":
                    resData.setData(sqlService.runDelete(sqlData));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("SQL执行错误:【{}】",e.getStackTrace());
            throw new BaseException(ReturnEnums.SQL_RUN_ERROR);
        }
        return resData;
    }
}
