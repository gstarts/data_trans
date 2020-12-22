package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.Photo;

import java.util.List;

/**
 * 车辆通关图片信息Mapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface PhotoMapper 
{
    /**
     * 查询车辆通关图片信息
     * 
     * @param id 车辆通关图片信息ID
     * @return 车辆通关图片信息
     */
     Photo selectPhotoById (Long id);

    /**
     * 查询车辆通关图片信息列表
     * 
     * @param sessionId 车辆通关图片信息
     * @return 车辆通关图片信息集合
     */
     Photo selectPhoto (String sessionId);

    /**
     * 新增车辆通关图片信息
     * 
     * @param photo 车辆通关图片信息
     * @return 结果
     */
     int insertPhoto (Photo photo);

    /**
     * 修改车辆通关图片信息
     * 
     * @param photo 车辆通关图片信息
     * @return 结果
     */
     int updatePhoto (Photo photo);

    /**
     * 删除车辆通关图片信息
     * 
     * @param id 车辆通关图片信息ID
     * @return 结果
     */
     int deletePhotoById (Long id);

    /**
     * 批量删除车辆通关图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deletePhotoByIds (Long[] ids);
}
