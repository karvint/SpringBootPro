package com.ty.show.service.codemirror.impl;

import com.ty.show.dataobject.entity.SqlData;
import com.ty.show.dataobject.mapper.codemirror.SqlMapper;
import com.ty.show.service.codemirror.SqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SqlServiceImpl implements SqlService {
    @Autowired
    SqlMapper sqlMapper;

    @Override
    public List<Map<String,Object>> runSelect(SqlData sqlData) {
        return sqlMapper.runSelect(sqlData);
    }

    @Override
    public Integer runUpdate(SqlData sqlData) {
        return sqlMapper.runUpdate(sqlData);
    }

    @Override
    public Integer runInsert(SqlData sqlData) {
        return sqlMapper.runInsert(sqlData);
    }

    @Override
    public Integer runDelete(SqlData sqlData) {
        return sqlMapper.runDelete(sqlData);
    }
}
