package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.VeRfid;

import java.util.List;


/**
 * 车辆通关时电子车牌信息Mapper接口
 * 
 * @author songqingchuan
 * @date 2020-06-22
 */
public interface GveRfidMapper
{
    /**
     * 查询车辆通关时电子车牌信息
     * 
     * @param id 车辆通关时电子车牌信息ID
     * @return 车辆通关时电子车牌信息
     */
     VeRfid selectGveRfidById(Long id);

    /**
     * 查询车辆通关时电子车牌信息列表
     * 
     * @param sessionId 车辆通关时电子车牌信息
     * @return 车辆通关时电子车牌信息集合
     */
     VeRfid selectGveRfid(String sessionId);

    /**
     * 新增车辆通关时电子车牌信息
     * 
     * @param gVeRfid 车辆通关时电子车牌信息
     * @return 结果
     */
     int insertGveRfid(VeRfid gVeRfid);

    /**
     * 修改车辆通关时电子车牌信息
     * 
     * @param gVeRfid 车辆通关时电子车牌信息
     * @return 结果
     */
     int updateGveRfid(VeRfid gVeRfid);

    /**
     * 删除车辆通关时电子车牌信息
     * 
     * @param id 车辆通关时电子车牌信息ID
     * @return 结果
     */
     int deleteGveRfidById(Long id);

    /**
     * 批量删除车辆通关时电子车牌信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteGveRfidByIds(Long[] ids);
}
