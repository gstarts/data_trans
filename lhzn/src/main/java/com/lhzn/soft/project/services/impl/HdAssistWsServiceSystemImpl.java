package com.lhzn.soft.project.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.lhzn.soft.framework.aspectj.enums.Docking;
import com.lhzn.soft.project.domain.*;
import com.lhzn.soft.project.mapper.GatherdataLogMapper;
import com.lhzn.soft.project.mapper.XmlContentMapper;
import com.lhzn.soft.project.services.BusinessSystemService;
import com.lhzn.soft.project.services.ForwardService;
import com.lhzn.soft.utils.StringUtils;
import com.lhzn.soft.utils.XmlUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 华东辅助平台实现
 *
 * @author qishuai
 */

@Service("HDFZ")
public class HdAssistWsServiceSystemImpl implements BusinessSystemService {
    @Value("${webservice.hd.wsdl}")
    private String wsdl;
    @Value("${webservice.hd.name}")
    private String namespace;
    @Value("${webservice.hd.method}")
    private String methodName;
    @Resource
    private GatherdataLogMapper glMapper;
    @Resource
    private XmlContentMapper xmlContentMapper;
    @Resource
    private ForwardService forwardService;
    private final static String ERR_CODE = "404";

    @Override
    public String createInteractiveXml(Map<String, ?> map) {
        GatherdataLog gatherdataLog = (GatherdataLog) map.get("gl");
        IcInfo icInfo = (IcInfo) map.get("ic");
        VeRfid veRfid = (VeRfid) map.get("ve");
        ContaRecognition contaRecognition = (ContaRecognition) map.get("cr");
        String contaId = contaRecognition.getContaId();
        // 创建一个Document实例
        Document doc = DocumentHelper.createDocument();

        Element root = doc.addElement("MESSAGE_LIST_1.1");

        Element oneChildElement = root.addElement("CARGO_HEAD_INFO");

        oneChildElement.addElement("AREA_ID")
                .addText(StringUtils.replaceNullString(gatherdataLog.getAreaId()));
        oneChildElement.addElement("CHNL_NO")
                .addText(StringUtils.replaceNullString(gatherdataLog.getChnlNo()));
        oneChildElement.addElement("I_E_TYPE")
                .addText(StringUtils.replaceNullString(gatherdataLog.getIEFlag()));
        oneChildElement.addElement("SEQ_NO")
                .addText(StringUtils.replaceNullString(gatherdataLog.getSessionId()));
        oneChildElement.addElement("DR_IC_NO")
                .addText(StringUtils.replaceNullString(icInfo.getIcNo()));
        oneChildElement.addElement("GROSS_WT")
                .addText(StringUtils.replaceNullString(gatherdataLog.getGrossWt()));
        oneChildElement.addElement("FILE_TIME")
                .addText(StringUtils.replaceNullString(gatherdataLog.getOperateTime()));

        List<String> strings = StringUtils.str2List(contaId, ",", true, true);
        if (strings.size() > 0) {
            oneChildElement.addElement("CONTA_ID_F")
                    .addText(StringUtils.replaceNullString(strings.get(0)));
        }
        if (strings.size() > 1) {
            oneChildElement.addElement("CONTA_ID_B")
                    .addText(StringUtils.replaceNullString(strings.get(1)));
        }
        oneChildElement.addElement("CAR_EC_NO")
                .addText(StringUtils.replaceNullString(veRfid.getRfidId()));
        oneChildElement.addElement("VE_NAME")
                .addText(StringUtils.replaceNullString(gatherdataLog.getVeLicenseNo()));
        oneChildElement.addElement("ESEAL_ID")
                .addText(gatherdataLog.getEsealId());

        // 输出xml文件
        String stringXml = doc.asXML();
        System.out.println("dom4j Create21 success!");
        System.out.println(stringXml);
        return stringXml;
    }

    @Override
    public String callServices(String reqXml) {
        return forwardService.callServices(reqXml, wsdl, namespace, methodName);
    }

    @Override
    public boolean isRelease(String resXml, String sessionId) {

        JSONObject json = JSONObject.parseObject(XmlUtil.xmlToJson(resXml));
        GatherdataLog gl = new GatherdataLog();
        String code = String.valueOf(json.get("code")) ;
        if (ERR_CODE.equals(code)) {
            gl.setCheckResult("N");
            gl.setOpHint("华东辅助平台业务系统调用超时");
            glMapper.updateGatherdataLog(gl);
            return true;
        }
        JSONObject root = (JSONObject) json.get("MESSAGE_LIST_1.2");
        if (StringUtils.isNull(root)) {
            return true;
        }
        // 获取返回值
        String checkResult = (String) root.get("CHK_RESULT");
        gl.setCheckResult(checkResult);
        String opHint = (String) root.get("LED_INFO");
        gl.setOpHint(opHint);
        gl.setSessionId(sessionId);
        glMapper.updateGatherdataLog(gl);
        return "Y".equals(checkResult);
    }

    @Override
    public void saveInteractiveXml(String reqXml, String resXml, String sessionId) {
        XmlContent xmlContent = new XmlContent();
        xmlContent.setSessionId(sessionId);
        xmlContent.setXmlName(Docking.HDFZ.name());
        xmlContent.setXmlContent(reqXml);
        xmlContent.setXmlResponse(resXml);
        xmlContentMapper.insertXmlContent(xmlContent);
    }

}
