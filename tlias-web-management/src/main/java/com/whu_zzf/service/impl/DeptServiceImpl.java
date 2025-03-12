package com.whu_zzf.service.impl;

import com.whu_zzf.mapper.DeptMapper;
import com.whu_zzf.pojo.Dept;
import com.whu_zzf.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }
    @Override
    public Dept getByid(Integer id) {
        return deptMapper.getByid(id);
    }
    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }
    @Override
    public void add(Dept dept) {
        //补充对象信息
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.add(dept);
    }
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        System.out.println("修改对象："+dept);
        deptMapper.update(dept);
    }
}
