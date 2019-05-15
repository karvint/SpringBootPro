package com.ty.show.service.impl;

import com.ty.show.dataobject.entity.Want;
import com.ty.show.dataobject.mapper.WantMapper;
import com.ty.show.service.WantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: tty
 * @date: 2019/5/9
 * @description:
 */
@Service
public class WantServiceImpl implements WantService {

    @Autowired
    WantMapper wantMapper;

    @Override
    public List<Want> getWantInfo(Want want) {
        return wantMapper.getWantInfo(want);
    }

    @Override
    public Integer insertWant(Want want) {
        return wantMapper.insertWant(want);
    }
}
