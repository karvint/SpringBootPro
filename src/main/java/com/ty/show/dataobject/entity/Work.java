package com.ty.show.dataobject.entity;

import lombok.Data;

/**
 * @author: cxm
 * @date: 2019/5/7
 * @description:
 */
@Data
public class Work {
    private int id;
    private int position;
    private int salary;
    private int area;
    private int userId;
    private int industry;
    private String type;
    private int needNum;
    private String title;

    private String username;
    private String address;
    private String tel;
    private String email;

}
