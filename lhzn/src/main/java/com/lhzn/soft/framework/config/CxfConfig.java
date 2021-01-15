package com.lhzn.soft.framework.config;

import com.lhzn.soft.webservices.WebServices0x81;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author gstarqs
 */
@Configuration
public class CxfConfig {
    private Bus bus;
    private WebServices0x81 webServices;

    @Autowired
    public CxfConfig(Bus bus, @Qualifier("webService0x81Impl") WebServices0x81 webServices) {
        this.bus = bus;
        this.webServices = webServices;
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, webServices);
        endpoint.publish("/parseXml");
        return endpoint;
    }


    @Bean
    public ServletRegistrationBean disServlet() {

        // 此处配置的是webservice接口的访问地址，类似 http://127.0.0.1:8001/emr
        CXFServlet cxfServlet = new CXFServlet();

        return new ServletRegistrationBean(new CXFServlet(), "/webServices/*");
    }

}
