package com.whu_zzf.mapper;

import com.whu_zzf.pojo.Emp;
import com.whu_zzf.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    //--------------------原始分页查询------------------------------//
//
////    查询总记录数
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
//    public Long count();
//
////    查询结果列表
//    @Select("select e.*, d.name deptName " +
//            "from emp e left join dept d on e.dept_id = d.id " +
//            "order by update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

//        @Select("select e.*, d.name deptName " +
//            "from emp e left join dept d on e.dept_id = d.id " +
//            "order by update_time desc ")

    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id") //返回数据库中的键
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, " +
            "entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByids(List<Integer> ids);

    Emp getByid(Integer id);

    void updateByid(Emp emp);

    List<Map<String, Object>> countEmpJobData();

    List<Map<String, Object>> countEmGenderData();

    /**
     *根据用户名密码查询员工信息
     */
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
