package com.whu_zzf.mapper;

import com.whu_zzf.pojo.Emp;
import com.whu_zzf.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     *批量保存员工工作信息
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工id批量删除员工工作信息
     */
    void deleteByempids(List<Integer> empids);
}
