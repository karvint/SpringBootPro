package com.ty.show.service.impl;

import com.ty.show.dataobject.entity.UploadFile;
import com.ty.show.dataobject.entity.User;
import com.ty.show.dataobject.entity.Want;
import com.ty.show.dataobject.entity.Work;
import com.ty.show.dataobject.mapper.LoginMapper;
import com.ty.show.dataobject.mapper.WantMapper;
import com.ty.show.dataobject.mapper.WorkMapper;
import com.ty.show.enums.FileEnums;
import com.ty.show.enums.ReturnEnums;
import com.ty.show.exception.BaseException;
import com.ty.show.service.WorkService;
import com.ty.show.utils.PathUtil;
import com.ty.show.utils.SpringUtil;
import com.ty.show.vo.UserVo;
import com.ty.show.vo.WorkInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author: cxm
 * @date: 2019/5/7
 * @description:
 */
@Service
@Slf4j
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkMapper workMapper;

    @Autowired
    WantMapper wantMapper;


    @Override
    public List<Work> getWorkInfoByParam(WorkInfoVo workInfoVo) {
        try {
            List<Work> works = workMapper.getWorkInfoByParam(workInfoVo);
            return works;
        } catch (Exception e) {
            log.error("查询工作类型失败:【{}】",e.getStackTrace());
            throw new BaseException(ReturnEnums.SQL_RUN_ERROR);
        }
    }

    @Override
    public Integer insertWork(Work work) {
        return workMapper.insertWork(work);
    }

    @Override
    public UploadFile uploadFiles(MultipartFile file, HttpServletRequest request) {
        try {
            String fileUrl = request.getParameter("fileUrl");
            Integer user = Integer.parseInt(request.getParameter("user"));
            Integer workId = Integer.parseInt(request.getParameter("workId"));
            Integer res = this.checkFileExistByFileUrl(fileUrl);// 查询验证文件是否存在
            if(res==0){
                String real = PathUtil.getSystemRealPath(request);// dev:/shceduler prod:jar包位置
                InputStream in;
                FileOutputStream out;
                try {
                    in = file.getInputStream();
                } catch (IOException e) {
                    log.error("文件读取异常：【{}】",e.getStackTrace());
                    throw new BaseException(FileEnums.FILE_INPUT_ERROR);
                }
                String savePath = real + fileUrl;
                File folder = new File(savePath);
                if(!folder.exists()){
                    folder.getParentFile().mkdirs();
                    try {
                        folder.createNewFile();
                    } catch (IOException e) {
                        log.error("文件创建异常：【{}】",e.getStackTrace());
                        throw new BaseException(FileEnums.FILE_CREATE_ERROR);
                    }
                    try {
                        out = new FileOutputStream(folder);
                        // 创建一个缓冲区
                        byte[] buffer = new byte[1024];
                        // 判断输入流中的数据是否已经读完的标识
                        int len = 0;
                        // 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while ((len = in.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                        in.close();
                        out.close();
                        UploadFile uploadFile = new UploadFile();
                        uploadFile.setExits(0);
                        uploadFile.setFileUrl(fileUrl);
                        uploadFile.setFileName(file.getOriginalFilename());
                        uploadFile.setUserId(user);
                        uploadFile.setWorkId(workId);
                        Integer re = workMapper.saveUploadFile(uploadFile);
                        return uploadFile;
                    } catch (FileNotFoundException e) {
                        log.error("文件路径未找到：【{}】",e.getStackTrace());
                        throw new BaseException(FileEnums.FILE_PATH_NOT_FOUND_ERROR);
                    } catch (IOException io){
                        log.error("文件存储异常：【{}】",io.getStackTrace());
                        throw new BaseException(FileEnums.FILE_OUTPUT_ERROR);
                    }
                }else{
                    log.error("该路径下文件已存在：【{}】",fileUrl);
                    throw new BaseException(FileEnums.FILE_EXIST_ERROR);
                }
            }else{
                log.error("该路径下文件已存在：【{}】",fileUrl);
                throw new BaseException(FileEnums.FILE_EXIST_ERROR);
            }
        } catch (BaseException e) {
            log.error("文件上传失败：【{}】",e.getStackTrace());
            throw new BaseException(FileEnums.FILE_UPLOAD_ERROR);
        }
    }

    public Integer checkFileExistByFileUrl(String fileUrl) {
        try {
            String active = SpringUtil.getActiveProfile();
            if("dev".equals(active)){
                fileUrl = "files/"+fileUrl;
            }else if("prod".equals(active)){
                fileUrl = "files\\"+fileUrl;
            }else{
                fileUrl = "files/"+fileUrl;
            }
            return workMapper.checkFileExistByFileUrl(fileUrl);
        } catch (Exception e) {
            log.error("检查公告附件地址异常:【{}】",e.getStackTrace());
            throw new BaseException(FileEnums.CHECK_FILE_URL_ERROR);
        }
    }

    @Override
    public Work introduce(Work work) {
        Want want = wantMapper.getWantByuserId(work);
        return workMapper.introduce(want);
    }
}
