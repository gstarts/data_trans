package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.DrRfid;

import java.util.List;

/**
 * 车辆通关司机卡信息Mapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface DrRfidMapper 
{
    /**
     * 查询车辆通关司机卡信息
     * 
     * @param id 车辆通关司机卡信息ID
     * @return 车辆通关司机卡信息
     */
    public DrRfid selectDrRfidById (Long id);

    /**
     * 查询车辆通关司机卡信息列表
     * 
     * @param sessionId sessionId
     * @return 车辆通关司机卡信息集合
     */
    public DrRfid selectDrRfid (String sessionId);

    /**
     * 新增车辆通关司机卡信息
     * 
     * @param drRfid 车辆通关司机卡信息
     * @return 结果
     */
    public int insertDrRfid (DrRfid drRfid);

    /**
     * 修改车辆通关司机卡信息
     * 
     * @param drRfid 车辆通关司机卡信息
     * @return 结果
     */
    public int updateDrRfid (DrRfid drRfid);

    /**
     * 删除车辆通关司机卡信息
     * 
     * @param id 车辆通关司机卡信息ID
     * @return 结果
     */
    public int deleteDrRfidById (Long id);

    /**
     * 批量删除车辆通关司机卡信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrRfidByIds (Long[] ids);
}
