package com.lhzn.soft.project.services;

import org.dom4j.Document;

import java.util.Map;

/**
 * 采集端报文接口
 * @author gstarqs
 */

public interface XmlCollectService<T> {
    /**
     * 81报文解析
     * @param document 81xml
     * @return 返回结果
     * @throws Exception （校验报文异常）
     */
    Map<String,T> xmlResolve ( Document document ) throws  Exception;

    /**
     * 82报文组成
     * @param map 82报文对象组合
     * @return 结果
     */
    String create82Xml (Map map);

}
