package com.ty.show.dataobject.mapper;

import com.ty.show.dataobject.entity.UploadFile;
import com.ty.show.dataobject.entity.Want;
import com.ty.show.dataobject.entity.Work;
import com.ty.show.vo.WorkInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkMapper {

    @Select("<script>SELECT w.id,w.position,w.salary,w.area,w.userId,w.industry,w.type,w.needNum,w.title,u.realname,u.address,u.tel,u.email from db_work w left join db_user u on u.id = w.userId where 1=1" +
            "<if test=\"salary!=0 and salary!=null\"> and w.salary = #{salary}</if>" +
            "<if test=\"area!=0 and area!=null\"> and w.area = #{area}</if>" +
            "<if test=\"industry!=0 and industry!=null\"> and w.industry = #{industry}</if>" +
            "<if test=\"position!=0 and position!=null\"> and w.position = #{position}</if>" +
            "</script>")
    List<Work> getWorkInfoByParam(WorkInfoVo workInfoVo);


    @Insert("insert into db_work(position,salary,area,userId,industry,type,needNum,title)" +
            "values (#{position},#{salary},#{area},#{userId},#{industry},#{type},#{needNum},#{title})")
    Integer insertWork(Work work);

    @Select("select count(id) from db_uploadfile where fileUrl = #{fileUrl}")
    Integer checkFileExistByFileUrl(String fileUrl);

    @Insert("INSERT INTO db_uploadfile(fileName,fileUrl,exits,uploadTime,userId,workId) VALUES " +
            "(#{fileName},#{fileUrl},#{exits},now(),#{userId},#{workId})")
    Integer saveUploadFile(UploadFile uploadFile);


    @Select("select w.*,u.username,u.address,u.tel,u.email from db_work w LEFT JOIN db_user u on u.id = w.userId " +
            " where industry = #{industry} and area = #{area} and salary = #{salary} and position = #{position} limit 1")
    Work introduce(Want want);
}
