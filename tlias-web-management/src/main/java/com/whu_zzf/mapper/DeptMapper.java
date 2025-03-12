package com.whu_zzf.mapper;

import com.whu_zzf.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DeptMapper {
    //查询所有部门
    @Select("select id, name, create_time, update_time from dept order by update_time desc ")
    List<Dept> findAll();

    //根据id查询部门
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getByid(Integer id);

    //删除对应部门
    @Select("delete from dept where id = #{id}")
    void delete(Integer id);

    //添加指定部门
    //#内检索实体类属性，需要驼峰命名
    @Select("insert into dept( name, create_time, update_time) value ( #{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);

    //修改部门信息
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

}
