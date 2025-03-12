package com.whu_zzf.service;

import com.whu_zzf.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 获取员工岗位数据
     */
    JobOption getEmpJobData();

    /**
     * 获取员工性别数据
     */
    List<Map<String, Object>> getEmpGenderData();

}
