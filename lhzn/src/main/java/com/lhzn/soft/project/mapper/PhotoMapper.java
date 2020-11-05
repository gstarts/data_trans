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
    public Photo selectPhotoById (Long id);

    /**
     * 查询车辆通关图片信息列表
     * 
     * @param sessionID 车辆通关图片信息
     * @return 车辆通关图片信息集合
     */
    public Photo selectPhoto (String sessionID);

    /**
     * 新增车辆通关图片信息
     * 
     * @param photo 车辆通关图片信息
     * @return 结果
     */
    public int insertPhoto (Photo photo);

    /**
     * 修改车辆通关图片信息
     * 
     * @param photo 车辆通关图片信息
     * @return 结果
     */
    public int updatePhoto (Photo photo);

    /**
     * 删除车辆通关图片信息
     * 
     * @param id 车辆通关图片信息ID
     * @return 结果
     */
    public int deletePhotoById (Long id);

    /**
     * 批量删除车辆通关图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePhotoByIds (Long[] ids);
}
