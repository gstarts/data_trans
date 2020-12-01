package com.lhzn.soft.project.services;

import com.lhzn.soft.project.domain.GatherdataLog;

/**
 * 采集端报文接口
 * @author qishuai
 * @date 2020-12-1
 */
public interface GatherDataLogService {

    /**
     * 查询过卡记录
     *
     * @param sessionId 过卡记录ID
     * @return 过卡记录
     */
    GatherdataLog selectGatherdataLogById (String sessionId );
}
