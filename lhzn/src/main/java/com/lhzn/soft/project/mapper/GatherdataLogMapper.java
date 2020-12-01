package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.GatherdataLog;

import java.util.List;

/**
 * 过卡记录Mapper接口
 *
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface GatherdataLogMapper {
    /**
     * 查询过卡记录
     *
     * @param sessionId 过卡记录ID
     * @return 过卡记录
     */
     GatherdataLog selectGatherdataLogById ( String sessionId );

    /**
     * 查询过卡记录列表
     *
     * @param sessionId 过卡记录
     * @return 过卡记录集合
     */
     GatherdataLog selectGatherdataLog ( String sessionId );

    /**
     * 新增过卡记录
     *
     * @param gatherdataLog 过卡记录
     * @return 结果
     */
     int insertGatherdataLog ( GatherdataLog gatherdataLog );

    /**
     * 修改过卡记录
     *
     * @param gatherdataLog 过卡记录
     * @return 结果
     */
     int updateGatherdataLog ( GatherdataLog gatherdataLog );

    /**
     * 删除过卡记录
     *
     * @param sessionId 过卡记录ID
     * @return 结果
     */
     int deleteGatherdataLogById ( String sessionId );

    /**
     * 批量删除过卡记录
     *
     * @param sessionIds 需要删除的数据ID
     * @return 结果
     */
     int deleteGatherdataLogByIds ( String[] sessionIds );
}
