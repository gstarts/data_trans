package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.project.domain.*;
import com.lhzn.soft.project.services.XmlHdService;
import com.lhzn.soft.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 华东业务实现
 * @author qishuai
 */
@Service
public class XmlHdServiceImpl implements XmlHdService {

    @Override
    public String createHdXml(Map map) {
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
        oneChildElement.addElement("CONTA_ID_F")
                .addText(StringUtils.replaceNullString(strings(contaId)[0]));
        oneChildElement.addElement("CONTA_ID_B")
                .addText(StringUtils.replaceNullString(strings(contaId)[1]));
        oneChildElement.addElement("CAR_EC_NO")
                .addText(StringUtils.replaceNullString(veRfid.getRfidId()));
        oneChildElement.addElement("VE_NAME")
                .addText(StringUtils.replaceNullString(gatherdataLog.getVeLicenseNo()));
        oneChildElement.addElement("ESEAL_ID")
                .addText(StringUtils.replaceNullString(stringBuilder(contaId)));

        // 输出xml文件
        String stringXml = doc.asXML();
        System.out.println("dom4j CreateDom4j success!");
        return stringXml;
    }

    public static String stringBuilder(String contaId) {
        String str = StringUtils.replaceNullString(contaId);
        StringBuilder stringBuilder = new StringBuilder();
        String[] idArry = str.trim().split(",");
        if (!"".equals(contaId) && null != contaId) {
            for (String nid : idArry) {
                stringBuilder.append(nid);
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public static String[] strings(String contaId) {
        String str = StringUtils.replaceNullString(contaId);
        String[] idArry = str.trim().split(",");
        return idArry;
    }


}
