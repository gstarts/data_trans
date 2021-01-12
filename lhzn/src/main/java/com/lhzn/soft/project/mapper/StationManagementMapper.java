package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.StationManagement;

import java.util.List;

/**
 *  场所管理Mapper接口
 * 
 * @author lanzhenyuan
 * @date 2020-11-16
 */
public interface StationManagementMapper 
{
    /**
     * 查询 场所管理
     * 
     * @param id  场所管理ID
     * @return  场所管理
     */
     StationManagement selectStationManagementById(Long id);

    /**
     * 查询 场所管理列表
     * 
     * @param StationManagement  场所管理
     * @return  场所管理集合
     */
     List<StationManagement> selectStationManagementList(StationManagement StationManagement);

    /**
     * 新增 场所管理
     * 
     * @param StationManagement  场所管理
     * @return 结果
     */
     int insertStationManagement(StationManagement StationManagement);

    /**
     * 修改 场所管理
     * 
     * @param StationManagement  场所管理
     * @return 结果
     */
     int updateStationManagement(StationManagement StationManagement);

    /**
     * 删除 场所管理
     * 
     * @param id  场所管理ID
     * @return 结果
     */
     int deleteStationManagementById(Long id);

    /**
     * 批量删除 场所管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteStationManagementByIds(Long[] ids);

    /**
     * 获取场所列表
     * @return 结果
     */
    List<StationManagement> distinctList();

    /**
     * 获取对接系统
     * @param chnlNo 通道号
     * @return 结果
     */
    String getDockingSystem(String chnlNo);
}
