package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.Extend;

/**
 * 车辆通关扩展字段Mapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface ExtendMapper 
{
    /**
     * 查询车辆通关扩展字段
     * 
     * @param id 车辆通关扩展字段ID
     * @return 车辆通关扩展字段
     */
     Extend selectExtendById (Long id);

    /**
     * 查询车辆通关扩展字段列表
     * 
     * @param sessionId sessionId
     * @return 车辆通关扩展字段集合
     */
     Extend selectExtend (String sessionId);

    /**
     * 新增车辆通关扩展字段
     * 
     * @param extend 车辆通关扩展字段
     * @return 结果
     */
     int insertExtend (Extend extend);

    /**
     * 修改车辆通关扩展字段
     * 
     * @param extend 车辆通关扩展字段
     * @return 结果
     */
     int updateExtend (Extend extend);

    /**
     * 删除车辆通关扩展字段
     * 
     * @param id 车辆通关扩展字段ID
     * @return 结果
     */
     int deleteExtendById (Long id);

    /**
     * 批量删除车辆通关扩展字段
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteExtendByIds (Long[] ids);
}
