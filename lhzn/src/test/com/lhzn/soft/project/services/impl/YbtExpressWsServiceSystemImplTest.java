package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.project.services.BusinessService;
import com.lhzn.soft.project.services.BusinessSystemService;
import com.lhzn.soft.project.services.XmlCollectService;
import com.lhzn.soft.utils.XmlUtil;
import org.dom4j.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class YbtExpressWsServiceSystemImplTest {


    @Resource
    private XmlCollectService xmlCollectService;
    @Resource(name  = "COLLECT")
    private BusinessSystemService systemService;
    @Resource
    private BusinessService service;
    @Test
    public void createInteractiveXml() throws Exception {
       String  data="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<GATHER_INFO>\n" +
               "  <I_E_FLAG>I</I_E_FLAG>\n" +
               "  <AREA_ID>CNCEK070017</AREA_ID>\n" +
               "  <CHNL_NO>22222</CHNL_NO>\n" +
               "  <GETHER_MODE>A</GETHER_MODE>\n" +
               "  <SESSION_ID>21312311114765</SESSION_ID>\n" +
               "  <IC_INFO>\n" +
               "    <IC_ID></IC_ID>\n" +
               "    <IC_NO></IC_NO>\n" +
               "    <IC_Type></IC_Type>\n" +
               "    <IC_EXTENDED_CONTENT></IC_EXTENDED_CONTENT>\n" +
               "  </IC_INFO>\n" +
               "  <FORM_INFO>\n" +
               "    <FORM_TYPE></FORM_TYPE>\n" +
               "    <FORM_ID></FORM_ID>\n" +
               "  </FORM_INFO>\n" +
               "  <VE_LICENSE_NO>蒙M19303</VE_LICENSE_NO>\n" +
               "  <CONTA_ID></CONTA_ID>\n" +
               "  <ESEAL_ID></ESEAL_ID>\n" +
               "  <GROSS_WT>16400</GROSS_WT>\n" +
               "  <VE_RFID>\n" +
               "    <RFID_ID></RFID_ID>\n" +
               "    <VE_LICENSE_NO></VE_LICENSE_NO>\n" +
               "    <VE_CUSTOMS_NO></VE_CUSTOMS_NO>\n" +
               "    <VE_WT>100</VE_WT>\n" +
               "    <VE_COMPANY></VE_COMPANY>\n" +
               "    <VE_PERFORMANCE></VE_PERFORMANCE>\n" +
               "  </VE_RFID>\n" +
               "  <DR_RFID>\n" +
               "    <RFID_ID></RFID_ID>\n" +
               "    <DR_NAME></DR_NAME>\n" +
               "    <DR_CUSTOMS_NO></DR_CUSTOMS_NO>\n" +
               "    <DR_COMPANY></DR_COMPANY>\n" +
               "    <DR_PERFORMANCE></DR_PERFORMANCE>\n" +
               "  </DR_RFID>\n" +
               "  <PHOTO>\n" +
               "    <PHOTO_GUID></PHOTO_GUID>\n" +
               "    <PHOTO_PERSPECTIVE></PHOTO_PERSPECTIVE>\n" +
               "  </PHOTO>\n" +
               "  <OPERATOR_ID></OPERATOR_ID>\n" +
               "  <OPERATE_TIME>2020-06-01 10:29:28</OPERATE_TIME>\n" +
               "  <EXTEND>\n" +
               "    <NAME></NAME>\n" +
               "    <VALUE></VALUE>\n" +
               "  </EXTEND>\n" +
               "  <CONTA_RECOGNITION>\n" +
               "    <CONTA_ID></CONTA_ID>\n" +
               "    <CONTA_TYPE></CONTA_TYPE>\n" +
               "    <CONTA_LOCK></CONTA_LOCK>\n" +
               "    <CONFIDENCE_RATIO></CONFIDENCE_RATIO>\n" +
               "  </CONTA_RECOGNITION>\n" +
               "  <VE_LICENSE_RECOGNITION>\n" +
               "    <DOMESTIC_LICENSE_NO></DOMESTIC_LICENSE_NO>\n" +
               "    <DOMESTIC_LICENSE_COLOR></DOMESTIC_LICENSE_COLOR>\n" +
               "    <FOREIGN_LICENSE_NO></FOREIGN_LICENSE_NO>\n" +
               "    <FOREIGN_LICENSE_COLOR></FOREIGN_LICENSE_COLOR>\n" +
               "    <CONFIDENCE_RATIO></CONFIDENCE_RATIO>\n" +
               "  </VE_LICENSE_RECOGNITION>\n" +
               "</GATHER_INFO>";

        String s = XmlUtil.xmlToJson(data);
        System.out.println(s);
        Document document = XmlUtil.stringToXml(data);
        // 报文解析
        Map<String, ?> map = xmlCollectService.xmlResolve(document);
       service.handle(map,"192.168.11.101");
    }
}