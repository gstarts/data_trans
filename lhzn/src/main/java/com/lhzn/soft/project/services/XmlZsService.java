package com.lhzn.soft.project.services;

import java.util.Map;

/**
 * 总署服务
 * @author qishuai
 */
public interface XmlZsService {
    /**
     * 生成总是所需json
     * @param map 对象
     * @return 结果
     */
    String createZsJson ( Map map);
}
