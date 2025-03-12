package com.whu_zzf.service;

import com.whu_zzf.pojo.Emp;
import com.whu_zzf.pojo.EmpQueryParam;
import com.whu_zzf.pojo.LoginInfo;
import com.whu_zzf.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 分页查询员工信息
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 保存员工信息
     */
    void save(Emp emp);

    /**
     * 批量删除员工信息
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信
     */
    void update(Emp emp);

    /**
     * 员工登录验证
     */
    LoginInfo login(Emp emp);
}
