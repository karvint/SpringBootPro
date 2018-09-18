package com.ty.show.dataobject.mapper.menu;

import com.ty.show.annotation.TargetDataSource;
import com.ty.show.dataobject.entity.menu.Menu;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    @Select("SELECT id,menu_name,url FROM db_menu")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "menu_name", property = "menuName"),
            @Result(column = "url", property = "url"),
    })
    @TargetDataSource("base")
    public List<Menu> getMenues();
}
