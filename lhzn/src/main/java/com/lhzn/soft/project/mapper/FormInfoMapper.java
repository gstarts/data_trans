package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.FormInfo;

import java.util.List;

/**
 * 车辆通关时单证号信息Mapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface FormInfoMapper 
{
    /**
     * 查询车辆通关时单证号信息
     * 
     * @param id 车辆通关时单证号信息ID
     * @return 车辆通关时单证号信息
     */
     FormInfo selectFormInfoById (Long id);

    /**
     * 查询车辆通关时单证号信息列表
     * 
     * @param sessionId 车辆通关时单证号信息
     * @return 车辆通关时单证号信息集合
     */
     FormInfo selectFormInfo (String sessionId);

    /**
     * 新增车辆通关时单证号信息
     * 
     * @param formInfo 车辆通关时单证号信息
     * @return 结果
     */
     int insertFormInfo (FormInfo formInfo);

    /**
     * 修改车辆通关时单证号信息
     * 
     * @param formInfo 车辆通关时单证号信息
     * @return 结果
     */
     int updateFormInfo (FormInfo formInfo);

    /**
     * 删除车辆通关时单证号信息
     * 
     * @param id 车辆通关时单证号信息ID
     * @return 结果
     */
     int deleteFormInfoById (Long id);

    /**
     * 批量删除车辆通关时单证号信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteFormInfoByIds (Long[] ids);
}
