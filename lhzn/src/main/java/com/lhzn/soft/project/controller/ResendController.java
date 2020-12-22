package com.lhzn.soft.project.controller;

import com.lhzn.soft.project.domain.GatherdataLog;
import com.lhzn.soft.project.domain.PlaceConfig;
import com.lhzn.soft.project.domain.XmlContent;
import com.lhzn.soft.project.services.ForwardService;
import com.lhzn.soft.project.services.GatherDataLogService;
import com.lhzn.soft.project.services.PlaceConfigService;
import com.lhzn.soft.project.services.XmlContentService;
import com.lhzn.soft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 重发
 * @author gstar
 */
@RestController
@RequestMapping("/resend/82xml")
public class ResendController {

    @Resource
    private XmlContentService xmlContentService;
    @Resource
    private GatherDataLogService gatherDataLogService;
    @Resource
    private PlaceConfigService placeConfigService;
    @Resource
    private ForwardService forwardService;
    @Value("${webservice.ct.wsdl}")
    private String ctWsdl;
    @Value("${webservice.ct.name}")
    private String ctNamespace;
    @Value("${webservice.ct.method}")
    private String ctMethodName;
    @GetMapping
    public String resend( @RequestParam("id") String id) {
        // 通过id 找到报文以及客户端ip地址
        XmlContent xmlContent = xmlContentService.selectXmlContentById(Long.valueOf(id));
        // 过卡记录对象(场所唯一编码)
        GatherdataLog gatherdataLog = gatherDataLogService.selectGatherdataLogById(xmlContent.getSessionId());
        // 获取网络地址
        String ip = placeConfigService.getIpAdders(gatherdataLog.getAreaId());
        if(StringUtils.isEmpty(ip)){
            return "404";
        }
        String wsdl = ip + ctWsdl;
        String response = forwardService.callCollectServices(xmlContent.getXmlContent(), "xml82", wsdl, ctNamespace, ctMethodName);
        if(StringUtils.isEmpty(response)){
            return "404";
        }
        return "200";
    }
}
