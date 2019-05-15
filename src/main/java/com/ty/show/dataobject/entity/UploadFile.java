package com.ty.show.dataobject.entity;

import lombok.Data;

@Data
public class UploadFile {
    private Integer id;
    private String fileName;
    private String fileUrl;
    private Integer exits;
    private String uploadTime;
    private Integer userId;
    private Integer workId;

}
