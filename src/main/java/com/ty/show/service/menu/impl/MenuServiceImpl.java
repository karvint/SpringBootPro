package com.ty.show.service.menu.impl;

import com.ty.show.dataobject.entity.menu.Menu;
import com.ty.show.dataobject.mapper.menu.MenuMapper;
import com.ty.show.service.menu.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> init() {
        return menuMapper.getMenues();
    }
}
