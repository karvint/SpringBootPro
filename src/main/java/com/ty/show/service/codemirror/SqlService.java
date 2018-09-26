package com.ty.show.service.codemirror;

import com.ty.show.dataobject.entity.SqlData;

import java.util.List;
import java.util.Map;

public interface SqlService {
    List<Map<String,Object>> runSelect(SqlData sqlData);

    Integer runUpdate(SqlData sqlData);

    Integer runInsert(SqlData sqlData);

    Integer runDelete(SqlData sqlData);
}
