package com.ty.show.vo;

import com.ty.show.dataobject.entity.User;
import lombok.Data;

/**
 * @author: tty
 * @date: 2019/5/12
 * @description:
 */
@Data
public class UserVo extends User {
    // ,w.industry,w.area,w.salary,w.position w.needNum,w.title
    private Integer industry;
    private Integer area;
    private Integer salary;
    private Integer position;
    private String companyType;
    private Integer needNum;
    private String title;
}
