package com.lhzn.soft.project.services;

import com.lhzn.soft.framework.exception.CustomException;
import com.lhzn.soft.project.domain.XmlContent;

import java.util.Map;

/**
 * 业务处理
 *
 * @author gstarqs
 */
public interface BusinessService {

    /**
     * 转发业务处理
     *
     * @param map 对象数据
     * @param ip  请求地址IP
     * @throws Exception 异常信息
     */
    void handle ( Map map, String ip ) throws Exception;

    /**
     * 业务校验
     *
     * @param map 对象数据
     * @throws CustomException 异常信息
     */
    void isCorrect ( Map map ) throws CustomException;

    /**
     * 将xml文件转成对象保存到数据库
     *
     * @param map 数据
     * @throws Exception 异常信息
     */
    void xmlToJavaBenSave ( Map map ) throws Exception;

    /**
     * 将交互信息保存到数据库备查
     *
     * @param xmlContent 数据对象
     */

    void saveXml ( XmlContent xmlContent );

    /**
     * 将报文保存到数据库中
     *
     * @param xml     报文原文
     * @param map     报文转换对象集合
     * @param xmlName 报文名
     * @return 结果
     */
    XmlContent genXml ( String xml, Map map, String xmlName );

    /**
     * 将报文保存到数据库中
     *
     * @param xml       报文原文
     * @param sessionId 报文序列号
     * @param xmlName   报文名
     * @return 结果
     */
    XmlContent genXml ( String xml, String sessionId, String xmlName );

}
