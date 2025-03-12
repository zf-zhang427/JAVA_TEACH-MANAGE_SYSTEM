package com.whu_zzf.controller;

import com.whu_zzf.pojo.Dept;
import com.whu_zzf.pojo.Result;
import com.whu_zzf.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts") //其余功能注解都是RequestMapping的衍生，所以对一样的路径统一管理
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    //查询部门
    @GetMapping
    public Result list(){
//        System.out.println("查询全部部门");
        log.info("查询全部部门");
        
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    //根据id查询部门
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id)
    {
//        System.out.println("根据id查询部门："+id);
        log.info("根据id查询部门：{}",id);
        Dept dept = deptService.getByid(id);
        return Result.success(dept);
    }
    //删除部门
    //前段请求参数名与服务器方法形参名一致
    @DeleteMapping
    public Result delete(Integer id)
    {
//        System.out.println("根据id删除部门："+id);
        log.info("根据id删除部门：{}",id);
        deptService.delete(id);
        return Result.success();
    }
    //新增部门
    @PostMapping
    //传递要求json格式，且内容与实体类对象一致
    public Result add(@RequestBody Dept dept)
    {
//        System.out.println("添加对象:" +dept);
        log.info("添加对象:{}" ,dept);
        deptService.add(dept);
        return Result.success();
    }
    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept)
    {
//        System.out.println("修改对象:" +dept);
        log.info("修改对象:{}" ,dept);
        deptService.update(dept);
        return Result.success();
    }
}
