package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.IcInfo;

import java.util.List;

/**
 * 车辆通关信息中ic卡信息Mapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface IcInfoMapper 
{
    /**
     * 查询车辆通关信息中ic卡信息
     * 
     * @param id 车辆通关信息中ic卡信息ID
     * @return 车辆通关信息中ic卡信息
     */
     IcInfo selectIcInfoById (Long id);

    /**
     * 查询车辆通关信息中ic卡信息列表
     * 
     * @param sessionId 车辆通关信息中ic卡信息
     * @return 车辆通关信息中ic卡信息集合
     */
     IcInfo selectIcInfo (String sessionId);

    /**
     * 新增车辆通关信息中ic卡信息
     * 
     * @param icInfo 车辆通关信息中ic卡信息
     * @return 结果
     */
     int insertIcInfo (IcInfo icInfo);

    /**
     * 修改车辆通关信息中ic卡信息
     * 
     * @param icInfo 车辆通关信息中ic卡信息
     * @return 结果
     */
     int updateIcInfo (IcInfo icInfo);

    /**
     * 删除车辆通关信息中ic卡信息
     * 
     * @param id 车辆通关信息中ic卡信息ID
     * @return 结果
     */
     int deleteIcInfoById (Long id);

    /**
     * 批量删除车辆通关信息中ic卡信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteIcInfoByIds (Long[] ids);
}
