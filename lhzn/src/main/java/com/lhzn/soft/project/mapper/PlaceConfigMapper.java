package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.PlaceConfig;

import java.util.List;

/**
 * 场所配置Mapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-11-30
 */
public interface PlaceConfigMapper 
{
    /**
     * 查询场所配置
     * 
     * @param id 场所配置ID
     * @return 场所配置
     */
     PlaceConfig selectPlaceConfigById(Long id);

    /**
     * 查询场所配置列表
     * 
     * @param placeConfig 场所配置
     * @return 场所配置集合
     */
     List<PlaceConfig> selectPlaceConfigList(PlaceConfig placeConfig);

    /**
     * 新增场所配置
     * 
     * @param placeConfig 场所配置
     * @return 结果
     */
     int insertPlaceConfig(PlaceConfig placeConfig);

    /**
     * 修改场所配置
     * 
     * @param placeConfig 场所配置
     * @return 结果
     */
     int updatePlaceConfig(PlaceConfig placeConfig);

    /**
     * 删除场所配置
     * 
     * @param id 场所配置ID
     * @return 结果
     */
     int deletePlaceConfigById(Long id);

    /**
     * 批量删除场所配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deletePlaceConfigByIds(Long[] ids);

    /**
     * 获取ip地址
     * @param id 场所id
     * @return 结果
     */
    String getIpAddersByPlaceId(String id);
}
