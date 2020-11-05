package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.GatherdataLog;

import java.util.List;

/**
 * qweMapper接口
 *
 * @author lanzhenyuan
 * @date 2020-06-18
 */
public interface GatherdataLogMapper {
    /**
     * 查询qwe
     *
     * @param sessionId qweID
     * @return qwe
     */
    public GatherdataLog selectGatherdataLogById ( String sessionId );

    /**
     * 查询qwe列表
     *
     * @param sessionId qwe
     * @return qwe集合
     */
    public GatherdataLog selectGatherdataLog ( String sessionId );

    /**
     * 新增qwe
     *
     * @param gatherdataLog qwe
     * @return 结果
     */
    public int insertGatherdataLog ( GatherdataLog gatherdataLog );

    /**
     * 修改qwe
     *
     * @param gatherdataLog qwe
     * @return 结果
     */
    public int updateGatherdataLog ( GatherdataLog gatherdataLog );

    /**
     * 删除qwe
     *
     * @param sessionId qweID
     * @return 结果
     */
    public int deleteGatherdataLogById ( String sessionId );

    /**
     * 批量删除qwe
     *
     * @param sessionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatherdataLogByIds ( String[] sessionIds );
}
