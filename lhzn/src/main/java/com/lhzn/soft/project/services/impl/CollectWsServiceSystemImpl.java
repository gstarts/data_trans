package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.framework.aspectj.enums.Docking;
import com.lhzn.soft.project.domain.*;
import com.lhzn.soft.project.mapper.*;
import com.lhzn.soft.project.services.BusinessSystemService;
import com.lhzn.soft.project.services.ForwardService;
import com.lhzn.soft.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 采集端接口实现
 * @author qishuai
 */
@Service("COLLECT")
public class CollectWsServiceSystemImpl implements BusinessSystemService {

    @Value("${webservice.ct.wsdl}")
    private String ctWsdl;
    @Value("${webservice.ct.name}")
    private String ctNamespace;
    @Value("${webservice.ct.method}")
    private String ctMethodName;
    @Resource
    private GatherdataLogMapper glMapper;
    @Resource
    private DrRfidMapper drRfidMapper;
    @Resource
    private ExtendMapper extendMapper;
    @Resource
    private FormInfoMapper formInfoMapper;
    @Resource
    private IcInfoMapper icInfoMapper;
    @Resource
    private PhotoMapper photoMapper;
    @Resource
    private VeLicenseRecognitionMapper veLicenseRecognitionMapper;
    @Resource
    private GveRfidMapper gVeRfidMapper;
    private String ip;
    @Resource
    private ForwardService forwardService;
    @Resource
    private ContaRecognitionMapper contaRecognitionMapper;
    @Resource
    private XmlContentMapper xmlContentMapper;

    @Override
    public String createInteractiveXml(Map<String, ?> map) {
        GatherdataLog gatherdataLog = (GatherdataLog) map.get("gl");
        IcInfo icInfo = (IcInfo) map.get("ic");
        VeRfid veRfid = (VeRfid) map.get("ve");
        // 创建一个Document实例
        Document doc = DocumentHelper.createDocument();
        // 添加根节点
        Element root = doc.addElement("GATHER_FEEDBACK");
        root.addElement("AREA_ID").addText(StringUtils.replaceNullString(gatherdataLog.getAreaId()));
        root.addElement("CHNL_NO").addText(StringUtils.replaceNullString(gatherdataLog.getChnlNo()));
        root.addElement("SESSION_ID").addText(StringUtils.replaceNullString(gatherdataLog.getSessionId()));
        root.addElement("RELLIST_TYPE").addText(StringUtils.replaceNullString(gatherdataLog.getRellistType()));
        root.addElement("RELLIST_ID_TYPE").addText(StringUtils.replaceNullString(gatherdataLog.getRellistIdType()));
        root.addElement("RELLIST_ID").addText(StringUtils.replaceNullString(gatherdataLog.getRellistId()));
        root.addElement("FEEDBACK_TIME").addText(StringUtils.replaceNullString(gatherdataLog.getFeedbackTime()));
        root.addElement("CHECK_RESULT").addText(StringUtils.replaceNullString(gatherdataLog.getCheckResult()));
        root.addElement("INSTRUCTION").addText(StringUtils.replaceNullString(gatherdataLog.getInstruction()));
        root.addElement("PROC_ERROR_CODE").addText(StringUtils.replaceNullString(gatherdataLog.getProcErrorCode()));
        root.addElement("PROC_ERROR_DESCRIPTION").addText(StringUtils.replaceNullString(gatherdataLog.getProcErrorDescription()));
        root.addElement("TECH_ERROR_CODE").addText(StringUtils.replaceNullString(gatherdataLog.getTechErrorCode()));
        root.addElement("TECH_ERROR_DESCRIPTION").addText(StringUtils.replaceNullString(gatherdataLog.getTechErrorDescription()));
        // 在IC_INFO根节点下添加第一个子节点
        createIcInfoNode(root, icInfo);
        //VE_INFO 在根节点下添加第一个子节点
        createVeInfoNode(root, veRfid);

        root.addElement("PACK_NO").addText(StringUtils.replaceNullString(gatherdataLog.getPackNo()));
        root.addElement("DECL_PACK").addText(StringUtils.replaceNullString(gatherdataLog.getDeclPack()));
        root.addElement("DECL_GOODS_WEIGHT").addText(StringUtils.replaceNullString(gatherdataLog.getDeclGoodsWeight()));
        root.addElement("OP_HINT").addText(StringUtils.replaceNullString(gatherdataLog.getOpHint()));
        root.addElement("LED_HINT").addText(StringUtils.replaceNullString(gatherdataLog.getLedHint()));
        root.addElement("EXTENDED_CONTENT").addText(StringUtils.replaceNullString(gatherdataLog.getExtendedContent()));

        System.out.println("dom4j create82Xml success!");
        System.out.println(doc.asXML());
        return doc.asXML();
    }

    @Override
    public String callServices(String reqXml) {
        String wsdl = ip + ctWsdl;
        return forwardService.callCollectServices(reqXml, "xml82", wsdl, ctNamespace, ctMethodName);
    }

    @Override
    public boolean isRelease(String resXml, String sessionId) {
        return false;
    }

    @Override
    public void saveInteractiveXml(String reqXml, String resXml, String sessionId) {
        XmlContent xmlContent = new XmlContent();
        xmlContent.setSessionId(sessionId);
        xmlContent.setXmlName(Docking.COLLECT.name());
        xmlContent.setXmlContent(reqXml);
        xmlContent.setXmlResponse(resXml);
        xmlContentMapper.insertXmlContent(xmlContent);
    }

    @Override
    public String setIpAddress(String ip) {
        return this.ip = ip;
    }

    @Override
    public Map<String, ?> createMap(String sessionId) {
        Map<String, Object> map = new HashMap<>(16);
        ContaRecognition contaRecognition = contaRecognitionMapper.selectContaRecognition(sessionId);
        map.put("cr", contaRecognition);
        DrRfid drRfid = drRfidMapper.selectDrRfid(sessionId);
        map.put("dr", drRfid);
        Extend extend = extendMapper.selectExtend(sessionId);
        map.put("extend", extend);
        FormInfo formInfo = formInfoMapper.selectFormInfo(sessionId);
        map.put("fi", formInfo);
        GatherdataLog gatherdataLog = glMapper.selectGatherdataLog(sessionId);
        map.put("gl", gatherdataLog);
        VeRfid veRfid = gVeRfidMapper.selectGveRfid(sessionId);
        map.put("ve", veRfid);
        IcInfo icInfo = icInfoMapper.selectIcInfo(sessionId);
        map.put("ic", icInfo);
        Photo photo = photoMapper.selectPhoto(sessionId);
        map.put("photo", photo);
        VeLicenseRecognition veLicenseRecognition = veLicenseRecognitionMapper.selectVeLicenseRecognition(sessionId);
        map.put("vr", veLicenseRecognition);
        return map;
    }


    /**
     * 创建 icInfo 节点
     */
    private void createIcInfoNode(Element root, IcInfo icInfo) {
        Element oneChildElement = root.addElement("IC_INFO");
        oneChildElement.addElement("IC_ID")
                .addText(StringUtils.replaceNullString(icInfo.getIcId()));
        oneChildElement.addElement("IC_NO")
                .addText(StringUtils.replaceNullString(icInfo.getIcNo()));
        oneChildElement.addElement("IC_Type")
                .addText(StringUtils.replaceNullString(icInfo.getIcType()));
        oneChildElement.addElement("IC_EXTENDED_CONTENT")
                .addText(StringUtils.replaceNullString(icInfo.getIcExtendedContent()));
    }

    /**
     * 创建VE节点
     */
    private void createVeInfoNode(Element root, VeRfid veRfid) {
        Element twoChildElement = root.addElement("VE_INFO");

        twoChildElement.addElement("VE_CUSTOMS_NO")
                .addText(StringUtils.replaceNullString(veRfid.getVeCustomsNo()));
        twoChildElement.addElement("VE_LICENSE_NO")
                .addText(StringUtils.replaceNullString(veRfid.getVeLicenseNo()));
        twoChildElement.addElement("VE_LICENSE_NO2")
                .addText(StringUtils.replaceNullString(veRfid.getVeLicenseNo2()));
        twoChildElement.addElement("DR_CUSTOMS_NO")
                .addText(StringUtils.replaceNullString(veRfid.getDrCustomsNo()));
        twoChildElement.addElement("DR_NAME")
                .addText(StringUtils.replaceNullString(veRfid.getDrName()));
        twoChildElement.addElement("TRAILER_ID")
                .addText(StringUtils.replaceNullString(veRfid.getTrailerId()));
        twoChildElement.addElement("WEIGHT_DIFF")
                .addText(StringUtils.replaceNullString(veRfid.getWeightDiff()));
        twoChildElement.addElement("GROSS_WEIGHT")
                .addText(StringUtils.replaceNullString(veRfid.getGrossWeight()));
        twoChildElement.addElement("VEHICLE_WEIGHT")
                .addText(StringUtils.replaceNullString(veRfid.getVehicleWeight()));

    }
}
