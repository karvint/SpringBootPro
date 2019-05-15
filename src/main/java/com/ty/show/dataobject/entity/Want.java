package com.ty.show.dataobject.entity;

import lombok.Data;

/**
 * @author: tty
 * @date: 2019/5/9
 * @description:
 */
@Data
public class Want extends User{
    private Integer id;
    private Integer userId;
    private Integer industry;
    private Integer area;
    private Integer salary;
    private Integer position;
}
