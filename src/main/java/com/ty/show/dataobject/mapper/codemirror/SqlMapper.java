package com.ty.show.dataobject.mapper.codemirror;

import com.ty.show.dataobject.entity.SqlData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SqlMapper {


    @Select("${sql}")
    List<Map<String,Object>> runSelect(SqlData sqlData);

    @Update("${sql}")
    Integer runUpdate(SqlData sqlData);

    @Insert("${sql}")
    Integer runInsert(SqlData sqlData);

    @Delete("${sql}")
    Integer runDelete(SqlData sqlData);
}
