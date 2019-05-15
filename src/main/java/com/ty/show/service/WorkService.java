package com.ty.show.service;

import com.ty.show.dataobject.entity.UploadFile;
import com.ty.show.dataobject.entity.Work;
import com.ty.show.vo.WorkInfoVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WorkService {
    List<Work> getWorkInfoByParam(WorkInfoVo workInfoVo);

    Integer insertWork(Work work);

    UploadFile uploadFiles(MultipartFile file, HttpServletRequest request);

    Work introduce(Work work);
}
