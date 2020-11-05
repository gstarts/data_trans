package com.lhzn.soft.webservices;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 处理采集端接口
 * @author gstarqs
 */
@Service

@WebService(name = "parseXml",    //该名字可自定义
        targetNamespace = "http://services/lhznsoft.com"    // 该URL一般为当前包名的倒序
)
public interface WebServices0x81 {
    /**
     *  处理采集端上传报文
     * @param data 上传数据
     * @param xmlType 报文类型
     * @return 结果
     * @throws IOException 异常
     * @throws SAXException 异常
     * @throws ParserConfigurationException 异常
     * @throws DocumentException 异常
     * @throws InterruptedException 异常
     * @throws NoSuchMethodException 异常
     * @throws IllegalAccessException 异常
     * @throws InvocationTargetException 异常
     */
    String parse81Xml ( @WebParam String data, String xmlType ) throws IOException, SAXException, ParserConfigurationException, DocumentException, InterruptedException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
