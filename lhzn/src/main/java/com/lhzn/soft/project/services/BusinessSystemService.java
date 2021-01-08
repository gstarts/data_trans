package com.lhzn.soft.project.services;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务系统接口
 * @author qishuai
 */
public interface BusinessSystemService {
    /**
     *  创建报文信息
     * @param map 报文对象信息
     * @return  交互报文
     */
    String createInteractiveXml(Map<String,?> map);
    /**
     * 调用webservice服务系统接口
     * @param reqXml 交互信息
     * @return 放行指令
     */
    String callServices (String reqXml );

    /**
     * 是否不放行
     * @param resXml 接收回执
     * @param sessionId 唯一id
     * @return 结果
     */
    boolean isNotRelease(String resXml,String sessionId);

    /**
     * 保存交互报文
     * @param reqXml 上传信息
     * @param resXml  回执信息
     * @param sessionId id
     */
    void saveInteractiveXml(String reqXml, String resXml, String sessionId);

    /**
     * 设置ip
     * @param ip ip
     * @return IP地址
     */
    default String setIpAddress(String ip){
       return ip;
    }

    /**
     * 采集服务器报文对象
     * @param sessionId 唯一组件
     * @return 结果
     */
    default Map createMap(String sessionId) {
        return new HashMap(16);
    }
}
