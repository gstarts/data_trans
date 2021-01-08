package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.project.domain.GatherdataLog;
import com.lhzn.soft.project.services.BusinessSystemService;

import java.util.Map;

/**
 * 业务处理
 * @author qishuai
 */
public class BusinessContext {

    private final BusinessSystemService systemService;

    public BusinessContext(BusinessSystemService systemService) {
        this.systemService = systemService;
    }

    /**
     * 业务处理类
     *
     * @param map 卡口报文对象
     * @return 结果
     */
    public boolean businessProcessing(Map<String, ?> map) {
        GatherdataLog gl = (GatherdataLog) map.get("gl");
        String sessionId = gl.getSessionId();
        String interactiveXml = systemService.createInteractiveXml(map);
        String resXml = systemService.callServices(interactiveXml);
        systemService.saveInteractiveXml(interactiveXml, resXml, sessionId);
        return systemService.isNotRelease(resXml, sessionId);
    }


    /**
     * 采集业务处理
     *
     * @param sessionId 唯一id
     * @param ip        采集端ip地址
     */
    public void collectBusinessProcessing(String sessionId, String ip) {
        systemService.setIpAddress(ip);
        String interactiveXml = systemService.createInteractiveXml(systemService.createMap(sessionId));
        String resXml = systemService.callServices(interactiveXml);
        systemService.saveInteractiveXml(interactiveXml, resXml, sessionId);
    }
}