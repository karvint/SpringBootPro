package com.ty.show.controller;

import com.ty.show.dataobject.ResData;
import com.ty.show.dataobject.entity.UploadFile;
import com.ty.show.dataobject.entity.Work;
import com.ty.show.enums.ReturnEnums;
import com.ty.show.service.WorkService;
import com.ty.show.vo.WorkInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: cxm
 * @date: 2019/5/7
 * @description:
 */
@RestController
@RequestMapping(value = "/work")
public class WorkController {

    @Autowired
    WorkService workService;

    /**
     * 工作列表
     * @param workInfoVo
     * @return
     */
    @PostMapping(value = "/info")
    public ResData getWorkInfoByParam(@RequestBody WorkInfoVo workInfoVo){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        List<Work> works = workService.getWorkInfoByParam(workInfoVo);
        resData.setRows(works);
        return resData;
    }

    /**
     * 注册时填写招聘信息
     * @param work
     * @return
     */
    @RequestMapping(value = "/insert")
    public ResData insertWork(@RequestBody Work work){
        ResData resData = new ResData(ReturnEnums.SUCCESS);
        Integer res = workService.insertWork(work);
        resData.setData(res);
        return resData;
    }


    /**
     * 上传附件
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public ResData uploadFiles(MultipartFile file, HttpServletRequest request){
        ResData res = new ResData(ReturnEnums.SUCCESS);
        UploadFile uploadFile = workService.uploadFiles(file,request);
        res.setData(uploadFile);
        return res;
    }

    /**
     * 推荐
     * @return
     */
    @RequestMapping(value = "/introduce")
    @ResponseBody
    public ResData introduce(@RequestBody Work work){
        ResData res = new ResData(ReturnEnums.SUCCESS);
        work = workService.introduce(work);
        res.setData(work);
        return res;
    }



}
