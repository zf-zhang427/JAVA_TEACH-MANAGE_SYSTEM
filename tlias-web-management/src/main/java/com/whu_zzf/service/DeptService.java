package com.whu_zzf.service;

import com.whu_zzf.pojo.Dept;

import java.util.List;

public interface DeptService {

    //查询所有部门
    List<Dept> findAll();
    //根据id查询部门
    Dept getByid(Integer id);
    //删除对应部门
    void delete(Integer id);
    //添加部门
    void add(Dept dept);
    //修改部门
    void update(Dept dept);
}
