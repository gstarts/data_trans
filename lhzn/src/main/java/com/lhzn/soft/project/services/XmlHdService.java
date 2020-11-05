package com.lhzn.soft.project.services;

import java.util.Map;

/**
 * 华东报文接口
 * @author gstarqs
 */
public interface XmlHdService {

    /**
     *  81报文组成
     * @param map 81报文对象
     * @return 结果
     */
    String createHdXml ( Map map);
}
