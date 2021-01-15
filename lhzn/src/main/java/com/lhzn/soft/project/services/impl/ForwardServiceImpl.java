package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.project.services.ForwardService;
import com.lhzn.soft.utils.DateUtils;
import org.apache.cxf.endpoint.Client;

import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;

/**
 * 转发服务
 *
 * @author gstarqs
 */
@Service
public class ForwardServiceImpl implements ForwardService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String callServices(String json, String wsdl, String namespace, String method) {
        StringBuffer str = new StringBuffer();
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            final Client client = dcf.createClient(wsdl);
            // 设置超时时间
            setOutTime(client, 60);
            // 指定命名空间和方法名
            QName name = new QName(namespace, method);
            //调用服务器响应结果
            Object[] objects;
            logger.info("请求外部接口时间" + DateUtils.getTime());
            objects = client.invoke(name, json);
            logger.info("接受回执数据时间" + DateUtils.getTime());
            logger.info("接受回执数据" + objects[0].toString());
            System.out.println(str.append(objects[0].toString()));
        } catch (Exception e) {
            e.printStackTrace();
            // 服务器请求超时
            str.append("{").append("code").append(":").append("404").append("}");
        }

        return str.toString();


    }

    @Override
    public String callCollectServices(String xml, String type, String wsdl, String namespace, String method) {
        StringBuilder str = new StringBuilder();
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(wsdl);
            // 设置请求超时时间
            setOutTime(client, 10);
            // 指定命名空间和方法名
            QName name = new QName(namespace, method);
            //调用服务器响应结果
            Object[] objects;
            // 请求数据时间写入日志文件
            logger.info("请求外部接口时间" + DateUtils.getTime());
            objects = client.invoke(name, xml, type);
            logger.info("接受回执数据时间" + DateUtils.getTime());
            logger.info("接受回执数据" + objects[0].toString());
            str.append(objects[0].toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            // 服务器请求超时
            str.append("{").append("code").append(":").append("404").append("}");
        }
        logger.info("调用服务返回值信息" + str.toString());
        return str.toString();
    }

    /**
     * 设置客户端超时时间
     *
     * @param client 客户端
     */
    private void setOutTime(Client client, int time) {
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy policy = new HTTPClientPolicy();
        policy.setConnectionTimeout(time * 1000);
        policy.setAllowChunking(false);
        policy.setReceiveTimeout(time * 1000);
        conduit.setClient(policy);
    }
}
