package com.ty.show.dataobject.mapper;

import com.ty.show.dataobject.entity.User;
import com.ty.show.vo.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginMapper {

    @Select("select count(1) from db_user where username=#{username}")
    Integer checkUsername(String username);

    @Select("select id,username,password,realname,address,type,tel,email,company from db_user where username=#{username} limit 1")
    User login(User user);

    @Select("<script>select u.id,u.username,u.realname,u.address,u.type,u.tel,u.email,u.company" +
            "<if test=\"type==1\">,w.industry,w.area,w.salary,w.position</if> " +
            "<if test=\"type==2\">,w.industry,w.area,w.salary,w.position,w.type companyType,w.needNum,w.title</if> " +
            "from db_user u " +
            "<if test=\"type==1\">left join db_want w on w.userId = u.id</if>" +
            "<if test=\"type==2\">left join db_work w on w.userId = u.id</if>" +
            "where 1=1 " +
            "<if test=\"username!=null and username!=''\">and username=#{username}</if>" +
            "<if test=\"realname!=null and realname!=''\">and realname like '%${realname}%'</if>" +
            "<if test=\"address!=null and address!=''\">and address=#{address}</if>" +
            "<if test=\"type!=null and type!=''\">and u.type=#{type}</if>" +
            "<if test=\"company!=null and company!=''\">and company like '%${company}%'</if>" +
            "</script>")
    List<UserVo> getUserList(User user);

    @Update("update db_user set password = '123456' where id =#{id}")
    Integer resetPwd(String id);

    @Insert("insert into db_user(username,password,realname,address,type,tel,email,company)" +
            "values (#{username},#{password},#{realname},#{address},#{type},#{tel},#{email},#{company})")
    Integer regsiter(User user);

    @Select("<script>select id,username,realname,address,type,tel,email,company from db_user where 1=1 " +
            "<if test=\"username!=null and username!=''\">and username=#{username}</if>" +
            "<if test=\"realname!=null and realname!=''\">and realname like '%${realname}%'</if>" +
            "<if test=\"address!=null and address!=''\">and address=#{address}</if>" +
            "<if test=\"type!=0 and type!=null\">and type=#{type}</if>" +
            "<if test=\"company!=null and company!=''\">and company like '%${company}%'</if> limit 1" +
            "</script>")
    User getUserListOne(User param);

    @Delete("delete from db_user where id =#{id}")
    Integer delete(String id);
}
