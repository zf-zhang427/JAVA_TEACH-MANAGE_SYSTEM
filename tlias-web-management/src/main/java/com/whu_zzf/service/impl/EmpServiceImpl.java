package com.whu_zzf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whu_zzf.mapper.EmpExprMapper;
import com.whu_zzf.mapper.EmpMapper;
import com.whu_zzf.pojo.*;
import com.whu_zzf.service.EmpService;
import com.whu_zzf.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
//    @Override
//    //@RequestParam(defaultValue = "1") 似乎没用？
//    public PageResult<Emp> page( Integer page,Integer pageSize) {
//        Long count = empMapper.count();
//        Integer start = (page - 1) * pageSize; //计数从零开始
//        List<Emp> rows = empMapper.list(start, pageSize);
//        return new PageResult<>(count, rows);
//    }
@Override
//@RequestParam(defaultValue = "1") 似乎没用？
public PageResult<Emp> page(EmpQueryParam empQueryParam) {
    //1.设置分页参数
    PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
    //2.执行查询
    List<Emp> empList = empMapper.list(empQueryParam);//条件查询参数
    //3.解析结果并封装
    Page<Emp> p = (Page<Emp>) empList;
    return new PageResult<Emp>(p.getTotal(),p.getResult());
}
    @Transactional //事务管理
    @Override
    public void save(Emp emp) {
        //1.补全更新时间
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //2.保存员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList))
        {
            //遍历集合，为empexpr实体类的empid赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工基本信息
        empMapper.deleteByids(ids);

        //2.批量删除员工工作经历
        empExprMapper.deleteByempids(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getByid(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据id修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateByid(emp);

        //2.根据id删除员工工作经历
        empExprMapper.deleteByempids(Arrays.asList(emp.getId()));
        //3.重新插入员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)) {
            //遍历集合，为empexpr实体类的empid赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1. 调用mapper接口, 根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        //2. 判断: 判断是否存在这个员工, 如果存在, 组装登录成功信息
        if(e != null){
            log.info("登录成功, 员工信息: {}", e);
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        //3. 不存在, 返回null
        return null;
    }
}
