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
import lombok.Data;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 易博特快件平台实现
 * @author qishuai
 */

@Service("YBTKJ")
public class YbtExpressWsServiceSystemImpl implements BusinessSystemService {
    @Value("${webservice.hd.wsdl}")
    private String wsdl;
    @Value("${webservice.hd.name}")
    private String namespace;
    @Value("${webservice.hd.method}")
    private String methodName;
    @Resource
    private XmlContentMapper xmlContentMapper;
    @Resource
    private GatherdataLogMapper glMapper;
    @Resource
    private ForwardService forwardService;
    private final static String ERR_CODE = "404";
    @Override
    public String createInteractiveXml(Map<String, ?> map) {
        Document doc = DocumentHelper.createDocument();
        GatherdataLog gatherdataLog = (GatherdataLog) map.get("gl");
        IcInfo icInfo = (IcInfo) map.get("ic");
        VeRfid veRfid = (VeRfid) map.get("ve");
        FormInfo formInfo = (FormInfo) map.get("fi");
        DrRfid drRfid = (DrRfid) map.get("dr");
        Photo photo = (Photo) map.get("photo");
        VeLicenseRecognition veLicense = (VeLicenseRecognition) map.get("vr");
        Extend extend = (Extend) map.get("extend");
        ContaRecognition recognition = (ContaRecognition) map.get("cr");
        // 添加根节点
        Element root = doc.addElement("GATHER_INFO");
        root.addElement("I_E_FLAG").addText(StringUtils.replaceNullString(gatherdataLog.getIEFlag()));
        root.addElement("AREA_ID").addText(StringUtils.replaceNullString(gatherdataLog.getAreaId()));
        root.addElement("CHNL_NO").addText(StringUtils.replaceNullString(gatherdataLog.getChnlNo()));
        // 关卡代码
        root.addElement("CUS_CODE").addText(StringUtils.replaceNullString(gatherdataLog.getCusCode()));
        root.addElement("SESSION_ID").addText(StringUtils.replaceNullString(gatherdataLog.getSessionId()));
        root.addElement("RPB_TYPE").addText(StringUtils.replaceNullString(gatherdataLog.getRpbType()));
        // 在根节点下添加第一个子节点
        Element oneChildElement = root.addElement("IC_INFO");
        oneChildElement.addElement("IC_ID")
                .addText(StringUtils.replaceNullString(icInfo.getIcId()));
        oneChildElement.addElement("IC_NO")
                .addText(StringUtils.replaceNullString(icInfo.getIcNo()));
        oneChildElement.addElement("IC_Type")
                .addText(StringUtils.replaceNullString(icInfo.getIcType()));
        oneChildElement.addElement("IC_EXTENDED_CONTENT")
                .addText(StringUtils.replaceNullString(icInfo.getIcExtendedContent()));
        root.addElement("DR_CUSTOMS_NO").addText(StringUtils.replaceNullString(gatherdataLog.getDrCustomsNo()));
        root.addElement("VE_CUSTOMS_NO").addText(StringUtils.replaceNullString(gatherdataLog.getVeCustomsNo()));
        root.addElement("VE_LICENSE_NO").addText(StringUtils.replaceNullString(gatherdataLog.getVeLicenseNo()));
        root.addElement("CONTA_ID").addText(StringUtils.replaceNullString(gatherdataLog.getContaId()));
        root.addElement("ESEAL_ID").addText(StringUtils.replaceNullString(gatherdataLog.getEsealId()));
        root.addElement("GROSS_WT").addText(StringUtils.replaceNullString(gatherdataLog.getGrossWt()));
        // 在根节点下添加第一个子节点
        Element twoChildElement = root.addElement("VE_RFID");
        twoChildElement.addElement("RFID_ID")
                .addText(StringUtils.replaceNullString(veRfid.getRfidId()));
        twoChildElement.addElement("VE_LICENSE_NO")
                .addText(StringUtils.replaceNullString(veRfid.getVeLicenseNo()));
        twoChildElement.addElement("VE_CUSTOMS_NO")
                .addText(StringUtils.replaceNullString(veRfid.getVeCustomsNo()));
        twoChildElement.addElement("VE_WT")
                .addText(StringUtils.replaceNullString(veRfid.getVeWt()));
        twoChildElement.addElement("VE_COMPANY")
                .addText(StringUtils.replaceNullString(veRfid.getVeCompany()));
        twoChildElement.addElement("VE_PERFORMANCE")
                .addText(StringUtils.replaceNullString(veRfid.getVePerformance()));
        // FORM_INFO
        createFormInfoNode(root,formInfo);
        // DR_RFID
        createRfidNode(root, drRfid);
        // photo
        createPhotoNode(root, photo);
        root.addElement("OPERATOR_ID").addText(StringUtils.replaceNullString(gatherdataLog.getOperatorId()));
        root.addElement("OPERATE_TIME").addText(StringUtils.replaceNullString(gatherdataLog.getOperateTime()));
        // EXTEND
        createExtendNode(root, extend);
        // CONTA_RECOGNITION
        createVeInfoNode(root, recognition);
        // VE_LICENSE_RECOGNITION
        createVeLicenseNode(root, veLicense);
        System.out.println("dom4j create81Xml success!");
        System.out.println(doc.asXML());
        return doc.asXML();
    }

    @Override
    public String callServices(String reqXml) {
        return forwardService.callServices(reqXml, wsdl, namespace, methodName);
    }

    @Override
    public boolean isNotRelease(String resXml, String sessionId) {
        JSONObject json = JSONObject.parseObject(XmlUtil.xmlToJson(resXml));
        GatherdataLog gl = new GatherdataLog();
        String code = String.valueOf(json.get("code")) ;
        if (ERR_CODE.equals(code)) {
            gl.setCheckResult("N");
            gl.setOpHint("易博特快件务系统调用超时");
            glMapper.updateGatherdataLog(gl);
            return true;
        }
        JSONObject root = (JSONObject) json.get("GATHER_FEEDBACK");
        if (StringUtils.isNull(root)) {
            return true;
        }
        // 获取返回值
        String checkResult = (String) root.get("CHECK_RESULT");
        gl.setCheckResult(checkResult);
        String opHint = (String) root.get("LED_HINT");
        gl.setOpHint(opHint);
        String led = (String) root.get("OP_HINT");
        gl.setLedHint(led);
        String session_id = (String) root.get("SESSION_ID");
        gl.setSessionId(session_id);
        glMapper.updateGatherdataLog(gl);
        return "N".equals(checkResult);
    }


    @Override
    public void saveInteractiveXml(String reqXml, String resXml, String sessionId) {
        XmlContent xmlContent = new XmlContent();
        xmlContent.setSessionId(sessionId);
        xmlContent.setXmlName(Docking.YBTKJ.name());
        xmlContent.setXmlContent(reqXml);
        xmlContent.setXmlResponse(resXml);
        xmlContentMapper.insertXmlContent(xmlContent);
    }


    private void createVeInfoNode(Element root, ContaRecognition recognition){
        Element sevenChildElement = root.addElement("CONTA_RECOGNITION");
        sevenChildElement.addElement("CONTA_ID")
                .addText(StringUtils.replaceNullString(recognition.getContaId()));
        sevenChildElement.addElement("CONTA_TYPE")
                .addText(StringUtils.replaceNullString(recognition.getContaType()));
        sevenChildElement.addElement("CONTA_LOCK")
                .addText(StringUtils.replaceNullString(recognition.getContaLock()));
        sevenChildElement.addElement("CONFIDENCE_RATIO")
                .addText(StringUtils.replaceNullString(recognition.getConfidenceRatio()));


    }

    private void createVeLicenseNode(Element root, VeLicenseRecognition veLicense){
        Element eightChildElement = root.addElement("VE_LICENSE_RECOGNITION");
        eightChildElement.addElement("DOMESTIC_LICENSE_NO")
                .addText(StringUtils.replaceNullString(veLicense.getDomesticLicenseNo()));
        eightChildElement.addElement("DOMESTIC_LICENSE_COLOR")
                .addText(StringUtils.replaceNullString(veLicense.getDomesticLicenseColor()));
        eightChildElement.addElement("FOREIGN_LICENSE_NO")
                .addText(StringUtils.replaceNullString(veLicense.getForeignLicenseNo()));
        eightChildElement.addElement("FOREIGN_LICENSE_COLOR")
                .addText(StringUtils.replaceNullString(veLicense.getForeignLicenseColor()));
        eightChildElement.addElement("CONFIDENCE_RATIO")
                .addText(StringUtils.replaceNullString(veLicense.getConfidenceRatio()));


    }

    private void createRfidNode(Element root, DrRfid drRfid){
        Element fourChildElement = root.addElement("DR_RFID");
        fourChildElement.addElement("RFID_ID")
                .addText(StringUtils.replaceNullString(drRfid.getRfidId()));
        fourChildElement.addElement("DR_NAME")
                .addText(StringUtils.replaceNullString(drRfid.getDrName()));
        fourChildElement.addElement("DR_CUSTOMS_NO")
                .addText(StringUtils.replaceNullString(drRfid.getDrCustomsNo()));
        fourChildElement.addElement("DR_COMPANY")
                .addText(StringUtils.replaceNullString(drRfid.getDrCompany()));
        fourChildElement.addElement("DR_PERFORMANCE")
                .addText(StringUtils.replaceNullString(drRfid.getDrPerformance()));


    }

    private void createPhotoNode(Element root, Photo photo){
        Element fiveChildElement = root.addElement("PHOTO");
        fiveChildElement.addElement("PHOTO_GUID")
                .addText(StringUtils.replaceNullString(photo.getPhotoGuid()));
        fiveChildElement.addElement("PHOTO_PERSPECTIVE")
                .addText(StringUtils.replaceNullString(photo.getPhotoPerspective()));


    }

    private void createFormInfoNode(Element root, FormInfo formInfo){
        Element threeChildElement = root.addElement("FORM_INFO");
        threeChildElement.addElement("FORM_TYPE")
                .addText(StringUtils.replaceNullString(formInfo.getFormType()));
        threeChildElement.addElement("FORM_ID")
                .addText(StringUtils.replaceNullString(formInfo.getFormId()));


    }

    private void createExtendNode(Element root, Extend extend){
        Element sixChildElement = root.addElement("EXTEND");
        sixChildElement.addElement("NAME")
                .addText(StringUtils.replaceNullString(extend.getName()));
        sixChildElement.addElement("VALUE")
                .addText(StringUtils.replaceNullString(extend.getValue()));
    }

}
