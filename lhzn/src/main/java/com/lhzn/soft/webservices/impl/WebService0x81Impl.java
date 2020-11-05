package com.lhzn.soft.webservices.impl;


import com.alibaba.fastjson.JSON;
import com.lhzn.soft.project.domain.XmlContent;
import com.lhzn.soft.project.services.BusinessService;
import com.lhzn.soft.project.services.XmlCollectService;
import com.lhzn.soft.utils.XmlUtil;
import com.lhzn.soft.webservices.WebServices0x81;
import com.lhzn.soft.webservices.domain.Result;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 报文处理
 *
 * @author gstarqs
 */
@Service
@WebService(name = "parseXml",    // 与接口中的name相同
        targetNamespace = "http://impl/services/lhznsoft.com"
)
public class WebService0x81Impl implements WebServices0x81 {
    protected Logger logger = LoggerFactory.getLogger (getClass ());
    @Resource
    private XmlCollectService xmlCollectService;
    @Resource
    private BusinessService businessHandleService;
    /**
     * 采集端报文名code
     */
    private static final String COLL_XML_NAME = "1";

    @Override
    public String parse81Xml ( String data, String xmlType ) {
        String ipAddress = getClientInfo ();
        StringBuilder buffer = new StringBuilder ();
        if (data.isEmpty () && xmlType.isEmpty ()) {
            return Result.err ("参数不能为空").toString ();
        }
        Document document;
        Map map = new HashMap<> (16);
        try {
            document = XmlUtil.stringToXml (data);
            // 报文解析
            map = xmlCollectService.xmlResolve (document);
            //   解析成功以后将数据保存到数据库
            businessHandleService.xmlToJavaBenSave (map);
            // 业务校验
            businessHandleService.isCorrect (map);
            // 将数据进行协议转换
            businessHandleService.handle (map, ipAddress);
            buffer.append (Result.success ());
        } catch (InterruptedException e) {
            e.printStackTrace ();
            logger.error (e.toString ());
            buffer.append (Result.err ("智能卡口数据交换平台线程异常，请重新发送"));
        } catch (DocumentException e) {
            e.printStackTrace ();
            buffer.append (Result.err (e.getMessage ()));
        } catch (DuplicateKeyException e) {
            logger.error (e.toString ());
            buffer.append (Result.err ("报文序列号重复"));

        } catch (Exception e) {
            logger.error (e.toString ());
            buffer.append (Result.err (e.getMessage ()));
        }

        // 将接收的报文和正常响应结果保存到数据库
        XmlContent xmlContent = businessHandleService.genXml (data, map, COLL_XML_NAME);
        xmlContent.setXmlResponse (buffer.toString ());
        businessHandleService.saveXml (xmlContent);
        //  将 对象转为json 串
        String jsonString = JSON.toJSONString (buffer);
        System.out.println (jsonString);
        return jsonString  ;

    }

    private String getClientInfo () {
        StringBuilder buffer = new StringBuilder ();
        buffer.append ("http://");
        try {
            Message message = PhaseInterceptorChain.getCurrentMessage ();
            HttpServletRequest request = (HttpServletRequest) message.get (AbstractHTTPDestination.HTTP_REQUEST);
            String remoteAddr = request.getRemoteAddr ();
            buffer.append (remoteAddr);
            buffer.append (":");
            System.out.println (buffer.toString ());
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return buffer.toString ();
    }
}
