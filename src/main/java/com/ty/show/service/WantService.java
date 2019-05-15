package com.ty.show.service;

import com.ty.show.dataobject.entity.Want;

import java.util.List;

public interface WantService {

    List<Want> getWantInfo(Want want);

    Integer insertWant(Want want);
}
