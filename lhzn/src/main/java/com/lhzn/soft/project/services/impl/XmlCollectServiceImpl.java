package com.lhzn.soft.project.services.impl;

import com.google.common.base.CaseFormat;
import com.lhzn.soft.project.domain.*;
import com.lhzn.soft.project.services.XmlCollectService;
import com.lhzn.soft.utils.StringUtils;
import com.lhzn.soft.utils.XmlUtil;
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
    public Map<String, Object> xmlResolve ( Document document ) throws Exception {
        GatherdataLog gl = new GatherdataLog ();
        Map<String, Object> rMap = new HashMap<> (9);
        for (Element element : (List<Element>) document.getRootElement ().elements ()) {
            if (element.elements ().size () > 0) {
                if (element.getName ().trim ().equals ("IC_INFO")) {
                    IcInfo ic = new IcInfo ();
                    for (Element childElement : (List<Element>) element.elements ()) {
                        genObj (ic, childElement);
                    }
                    rMap.put ("ic", ic);
                }
                if (element.getName ().trim ().equals ("FORM_INFO")) {
                    FormInfo fi = new FormInfo ();
                    for (Element childElement : (List<Element>) element.elements ()) {
                        genObj (fi, childElement);
                    }
                    rMap.put ("fi", fi);
                }
                if (element.getName ().trim ().equals ("VE_RFID")) {
                    VeRfid veRfid = new VeRfid ();
                    for (Element childElement : (List<Element>) element.elements ()) {
                        genObj (veRfid, childElement);
                    }
                    rMap.put ("ve", veRfid);
                }
                if (element.getName ().trim ().equals ("DR_RFID")) {
                    DrRfid dr = new DrRfid ();
                    for (Element childElement : (List<Element>) element.elements ()) {
                        genObj (dr, childElement);
                    }
                    rMap.put ("dr", dr);

                }
                if (element.getName ().trim ().equals ("PHOTO")) {
                    Photo photo = new Photo ();
                    for (Element childElement : (List<Element>) element.elements ()) {
                        genObj (photo, childElement);
                    }
                    rMap.put ("photo", photo);
                }
                if (element.getName ().trim ().equals ("EXTEND")) {
                    Extend extend = new Extend ();
                    for (Element childElement : (List<Element>) element.elements ()) {
                        genObj (extend, childElement);
                    }
                    rMap.put ("extend", extend);
                }
                if (element.getName ().trim ().equals ("CONTA_RECOGNITION")) {
                    ContaRecognition cr = new ContaRecognition ();
                    for (Element childElement : (List<Element>) element.elements ()) {
                        genObj (cr, childElement);
                    }
                    rMap.put ("cr", cr);

                }
                if (element.getName ().trim ().equals ("VE_LICENSE_RECOGNITION")) {
                    VeLicenseRecognition vr = new VeLicenseRecognition ();
                    for (Element childElement : (List<Element>) element.elements ()) {
                        genObj (vr, childElement);
                    }
                    rMap.put ("vr", vr);
                }
            } else {
                genObj (gl, element);
            }
        }
        rMap.put ("gl", gl);

        return rMap;
    }


    public <D> void genObj ( D d, Element element ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String guava = CaseFormat.UPPER_UNDERSCORE.to (CaseFormat.UPPER_CAMEL, element.getName ());
        Method method = d.getClass ().getMethod ("set" + guava, String.class);
        method.invoke (d, element.getTextTrim ());
    }

    @Override
    public String create82Xml ( Map map ) {
        GatherdataLog gatherdataLog = (GatherdataLog) map.get ("gl");
        IcInfo icInfo = (IcInfo) map.get ("ic");
        VeRfid veRfid = (VeRfid) map.get ("ve");

        // 创建一个Document实例
        Document doc = DocumentHelper.createDocument ();

        // 添加根节点
        Element root = doc.addElement ("GATHER_FEEDBACK");
        Element root01 = root.addElement ("AREA_ID").addText (StringUtils.replaceNullString (gatherdataLog.getAreaId ()));

        Element root02 = root.addElement ("CHNL_NO").addText (StringUtils.replaceNullString (gatherdataLog.getChnlNo ()));

        Element root03 = root.addElement ("SESSION_ID").addText (StringUtils.replaceNullString (gatherdataLog.getSessionId ()));

        Element root04 = root.addElement ("RELLIST_TYPE").addText (StringUtils.replaceNullString (gatherdataLog.getRellistType ()));
        Element root05 = root.addElement ("RELLIST_ID_TYPE").addText (StringUtils.replaceNullString (gatherdataLog.getRellistIdType ()));
        Element root06 = root.addElement ("RELLIST_ID").addText (StringUtils.replaceNullString (gatherdataLog.getRellistId ()));
        Element root07 = root.addElement ("FEEDBACK_TIME").addText (StringUtils.replaceNullString (gatherdataLog.getFeedbackTime ()));
        Element root08 = root.addElement ("CHECK_RESULT").addText (StringUtils.replaceNullString (gatherdataLog.getCheckResult ()));
        Element root09 = root.addElement ("INSTRUCTION").addText (StringUtils.replaceNullString (gatherdataLog.getInstruction ()));
        Element root10 = root.addElement ("PROC_ERROR_CODE").addText (StringUtils.replaceNullString (gatherdataLog.getProcErrorCode ()));
        Element root11 = root.addElement ("PROC_ERROR_DESCRIPTION").addText (StringUtils.replaceNullString (gatherdataLog.getProcErrorDescription ()));
        Element root12 = root.addElement ("TECH_ERROR_CODE").addText (StringUtils.replaceNullString (gatherdataLog.getTechErrorCode ()));
        Element root13 = root.addElement ("TECH_ERROR_DESCRIPTION").addText (StringUtils.replaceNullString (gatherdataLog.getTechErrorDescription ()));


        // IC_INFO
        // 在根节点下添加第一个子节点

        Element oneChildElement = root.addElement ("IC_INFO");

        oneChildElement.addElement ("IC_ID")
                .addText (StringUtils.replaceNullString (icInfo.getIcId ()));
        oneChildElement.addElement ("IC_NO")
                .addText (StringUtils.replaceNullString (icInfo.getIcNo ()));
        oneChildElement.addElement ("IC_Type")
                .addText (StringUtils.replaceNullString (icInfo.getIcType ()));
        oneChildElement.addElement ("IC_EXTENDED_CONTENT")
                .addText (StringUtils.replaceNullString (icInfo.getIcExtendedContent ()));

        // VE_INFO
        // 在根节点下添加第一个子节点
        Element twoChildElement = root.addElement ("VE_INFO");

        twoChildElement.addElement ("VE_CUSTOMS_NO")
                .addText (StringUtils.replaceNullString (veRfid.getVeCustomsNo ()));
        twoChildElement.addElement ("VE_LICENSE_NO")
                .addText (StringUtils.replaceNullString (veRfid.getVeLicenseNo ()));
        twoChildElement.addElement ("VE_LICENSE_NO2")
                .addText (StringUtils.replaceNullString (veRfid.getVeLicenseNo2 ()));
        twoChildElement.addElement ("DR_CUSTOMS_NO")
                .addText (StringUtils.replaceNullString (veRfid.getDrCustomsNo ()));
        twoChildElement.addElement ("DR_NAME")
                .addText (StringUtils.replaceNullString (veRfid.getDrName ()));
        twoChildElement.addElement ("TRAILER_ID")
                .addText (StringUtils.replaceNullString (veRfid.getTrailerId ()));
        twoChildElement.addElement ("WEIGHT_DIFF")
                .addText (StringUtils.replaceNullString (veRfid.getWeightDiff ()));
        twoChildElement.addElement ("GROSS_WEIGHT")
                .addText (StringUtils.replaceNullString (veRfid.getGrossWeight ()));
        twoChildElement.addElement ("VEHICLE_WEIGHT")
                .addText (StringUtils.replaceNullString (veRfid.getVehicleWeight ()));


        Element root14 = root.addElement ("PACK_NO").addText (StringUtils.replaceNullString (gatherdataLog.getPackNo ()));
        Element root15 = root.addElement ("DECL_PACK").addText (StringUtils.replaceNullString (gatherdataLog.getDeclPack ()));
        Element root16 = root.addElement ("DECL_GOODS_WEIGHT").addText (StringUtils.replaceNullString (gatherdataLog.getDeclGoodsWeight ()));
        Element root17 = root.addElement ("OP_HINT").addText (StringUtils.replaceNullString (gatherdataLog.getOpHint ()));
        Element root18 = root.addElement ("LED_HINT").addText (StringUtils.replaceNullString (gatherdataLog.getLedHint ()));
        Element root19 = root.addElement ("EXTENDED_CONTENT").addText (StringUtils.replaceNullString (gatherdataLog.getExtendedContent ()));


        OutputFormat oFormat = OutputFormat.createPrettyPrint ();
        oFormat.setEncoding ("UTF-8");
        StringWriter sWriter = new StringWriter ();
        XMLWriter xWriter = new XMLWriter (sWriter, oFormat);
        try {
            xWriter.write (doc);
            xWriter.flush ();
            xWriter.close ();
        } catch (IOException e) {
            System.err.println ("转换xml异常！");
        }
        String xml2 = sWriter.toString ();

        System.out.println ("dom4j create82Xml success!");
        return xml2;
    }


}