package com.lhzn.soft.project.services;

/**
 * 场所配置
 * @author qishuai
 */
public interface PlaceConfigService {

    /**
     *  获取ip 地址
     * @param locationId 场所id
     * @return 结果
     */
    String getIpAdders(String locationId);

}
