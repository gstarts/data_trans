package com.lhzn.soft.project.services.impl;

import com.google.common.base.CaseFormat;
import com.lhzn.soft.project.domain.*;
import com.lhzn.soft.project.services.XmlCollectService;
import com.lhzn.soft.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 81报文处理
 *
 * @author gstarqs
 */
@Service
public class XmlCollectServiceImpl<T> implements XmlCollectService<Object> {
    @Override
    public Map<String, Object> xmlResolve(Document document) throws Exception {
        GatherdataLog gl = new GatherdataLog();
        Map<String, Object> rMap = new HashMap<>(9);
        for (Element element : (List<Element>) document.getRootElement().elements()) {
            if (element.elements().size() > 0) {
                if ("IC_INFO".equals(element.getName().trim())) {
                    IcInfo ic = new IcInfo();
                    for (Element childElement : (List<Element>) element.elements()) {
                        genObj(ic, childElement);
                    }
                    rMap.put("ic", ic);
                }
                if ("FORM_INFO".equals(element.getName().trim())) {
                    FormInfo fi = new FormInfo();
                    for (Element childElement : (List<Element>) element.elements()) {
                        genObj(fi, childElement);
                    }
                    rMap.put("fi", fi);
                }
                if ("VE_RFID".equals(element.getName().trim())) {
                    VeRfid veRfid = new VeRfid();
                    for (Element childElement : (List<Element>) element.elements()) {
                        genObj(veRfid, childElement);
                    }
                    rMap.put("ve", veRfid);
                }
                if ("DR_RFID".equals(element.getName().trim())) {
                    DrRfid dr = new DrRfid();
                    for (Element childElement : (List<Element>) element.elements()) {
                        genObj(dr, childElement);
                    }
                    rMap.put("dr", dr);

                }
                if ("PHOTO".equals(element.getName().trim())) {
                    Photo photo = new Photo();
                    for (Element childElement : (List<Element>) element.elements()) {
                        genObj(photo, childElement);
                    }
                    rMap.put("photo", photo);
                }
                if ("EXTEND".equals(element.getName().trim())) {
                    Extend extend = new Extend();
                    for (Element childElement : (List<Element>) element.elements()) {
                        genObj(extend, childElement);
                    }
                    rMap.put("extend", extend);
                }
                if ("CONTA_RECOGNITION".equals(element.getName().trim())) {
                    ContaRecognition cr = new ContaRecognition();
                    for (Element childElement : (List<Element>) element.elements()) {
                        genObj(cr, childElement);
                    }
                    rMap.put("cr", cr);

                }
                if ("VE_LICENSE_RECOGNITION".equals(element.getName().trim())) {
                    VeLicenseRecognition vr = new VeLicenseRecognition();
                    for (Element childElement : (List<Element>) element.elements()) {
                        genObj(vr, childElement);
                    }
                    rMap.put("vr", vr);
                }
            } else {
                genObj(gl, element);
            }
        }
        rMap.put("gl", gl);

        return rMap;
    }


    public <D> void genObj(D d, Element element) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String guava = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, element.getName());
        Method method = d.getClass().getMethod("set" + guava, String.class);
        method.invoke(d, element.getTextTrim());
    }

    @Override
    public String create82Xml(Map map) {
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


        OutputFormat oFormat = OutputFormat.createPrettyPrint();
        oFormat.setEncoding("UTF-8");
        StringWriter sWriter = new StringWriter();
        XMLWriter xWriter = new XMLWriter(sWriter, oFormat);
        try {
            xWriter.write(doc);
            xWriter.flush();
            xWriter.close();
        } catch (IOException e) {
            System.err.println("转换xml异常！");
        }
        String xml2 = sWriter.toString();

        System.out.println("dom4j create82Xml success!");
        return xml2;
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
}