package com.lhzn.soft.project.services;

import com.lhzn.soft.framework.exception.CustomException;

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
    void isCorrect (Map<String,?> map) throws CustomException;

    /**
     * 将xml文件转成对象保存到数据库
     *
     * @param map 数据
     * @throws Exception 异常信息
     */
    void xmlToJavaBenSave ( Map<String,?> map ) throws Exception;

    /**
     * 保存报文信息
     * @param xml 报文信息
     * @param resJson 回复信息
     * @param map 报文对象
     */
    void genXml(String xml,String resJson, Map map);
}
