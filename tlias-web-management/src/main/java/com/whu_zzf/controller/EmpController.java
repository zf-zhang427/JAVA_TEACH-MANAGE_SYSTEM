package com.whu_zzf.controller;


import com.whu_zzf.pojo.Emp;
import com.whu_zzf.pojo.EmpQueryParam;
import com.whu_zzf.pojo.PageResult;
import com.whu_zzf.pojo.Result;
import com.whu_zzf.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
/**
 * 员工管理控制器
 */
public class EmpController {

//    调用接口方法记得创建一个对象
    @Autowired //自动注入
    private EmpService empService;
    @GetMapping
    /*
    * 分页查询
    * 输入参数：当前页码与每页展示数量
     */
    public Result page(EmpQueryParam empQueryParam)
    {
        log.info("分页请求参数: {}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 员工信息录入
     */
    @PostMapping
    public Result save(@RequestBody Emp emp)
    {
        log.info("请求参数emp：{}",emp);
        empService.save(emp);
        return  Result.success();
    }
    /**
     * 删除员工
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工:{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    /**
     * 通过id查询员工信息
     */
    @GetMapping("/{id}")
    public Result gatInfo(@PathVariable Integer id){
        log.info("通过id查询员工信息:{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }
    /**
     * 更新员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
