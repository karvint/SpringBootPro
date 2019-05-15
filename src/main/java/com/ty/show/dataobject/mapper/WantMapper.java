package com.ty.show.dataobject.mapper;

import com.ty.show.dataobject.entity.Want;
import com.ty.show.dataobject.entity.Work;
import com.ty.show.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WantMapper {

    @Select("<script>select id,userId,industry,area,salary,position from db_want where 1=1" +
            "<if test=\"industry!=0 and industry!=null\">and industry=#{industry}</if>" +
            "<if test=\"salary!=0 and salary!=null\">and salary=#{salary}</if>" +
            "<if test=\"area!=0 and area!=null\">and area=#{area}</if>" +
            "<if test=\"position!=0 and position!=null\">and position=#{position}</if>" +
            "</script>")
    List<Want> getWantInfo(Want want);

    @Insert("insert into db_want(userId,industry,area,salary,position)" +
            "values(#{userId},#{industry},#{area},#{salary},#{position})")
    Integer insertWant(Want want);

    @Select("select * from db_want where userId = #{id} limit 1")
    Want getWantByuserId(Work work);
}
