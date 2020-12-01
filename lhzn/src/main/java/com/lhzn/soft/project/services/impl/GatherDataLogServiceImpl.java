package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.project.domain.GatherdataLog;
import com.lhzn.soft.project.mapper.GatherdataLogMapper;
import com.lhzn.soft.project.services.GatherDataLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class GatherDataLogServiceImpl implements GatherDataLogService {
    @Resource
    private GatherdataLogMapper gatherdataLogMapper;

    @Override
    public GatherdataLog selectGatherdataLogById(String sessionId) {
        return gatherdataLogMapper.selectGatherdataLogById(sessionId);
    }

}
