package com.ty.show.vo;

import com.ty.show.dataobject.entity.Pagination;
import lombok.Data;

/**
 * @author: cxm
 * @date: 2019/5/7
 * @description:
 */
@Data
public class WorkInfoVo {
    private Integer salary;
    private Integer position;
    private Integer industry;
    private Integer area;
    private Pagination pagination;
}
