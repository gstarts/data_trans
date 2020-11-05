package com.lhzn.soft.project.services;

/**
 * 转发接口
 *
 * @author gstarqs
 */
public interface ForwardService {
    /**
     * 采集端服务调用
     *
     * @param json      81转发报文
     * @param wsdl      服务地址
     * @param namespace 命名空间
     * @param method    方法
     * @return 结果
     */
    String callServices ( String json, String wsdl, String namespace, String method );

    /**
     * 采集端服务调用
     *
     * @param xml       82报文
     * @param type      报文类型
     * @param wsdl      服务地址
     * @param namespace 命名空间
     * @param method    方法
     * @return 结果
     */
    String callCollectServices ( String xml, String type, String wsdl, String namespace, String method );

}
