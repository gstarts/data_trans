package com.lhzn.soft.project.services.impl;


import com.alibaba.fastjson.JSONObject;
import com.lhzn.soft.framework.aspectj.lang.MyAnnotation;
import com.lhzn.soft.framework.aspectj.lang.ValidateResult;
import com.lhzn.soft.framework.exception.CustomException;
import com.lhzn.soft.project.domain.*;
import com.lhzn.soft.project.mapper.*;
import com.lhzn.soft.project.services.*;
import com.lhzn.soft.utils.StringUtils;
import com.lhzn.soft.utils.XmlUtil;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 业务处理
 *
 * @author qishuai
 */
@Service
public class BusinessServiceImpl implements BusinessService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private XmlHdService xmlhdservice;
    @Resource
    private XmlCollectService xmlCollectService;
    @Resource
    private XmlZsService xmlzsService;
    @Resource
    private GatherdataLogMapper glMapper;
    @Resource
    private ContaRecognitionMapper contaRecognitionMapper;
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
    @Resource
    private ForwardService forwardService;
    @Resource
    private XmlContentMapper xmlContentMapper;
    @Value("${webservice.zs.wsdl}")
    private String zsWsdl;
    @Value("${webservice.zs.name}")
    private String zsNamespace;
    @Value("${webservice.zs.method}")
    private String zsMethodName;
    @Value("${webservice.hd.wsdl}")
    private String hdWsdl;
    @Value("${webservice.hd.name}")
    private String hdNamespace;
    @Value("${webservice.hd.method}")
    private String hdMethodName;
    @Value("${webservice.ct.wsdl}")
    private String ctWsdl;
    @Value("${webservice.ct.name}")
    private String ctNamespace;
    @Value("${webservice.ct.method}")
    private String ctMethodName;
    /**
     * 华东报文交互名
     */
    private static final String HD_XML_NAME = "2";
    /**
     * 总署报文交互名
     */
    private static final String ZS_XML_NAME = "3";
    /**
     * 采集端82报文
     */
    private static final String CT_XML_NAME = "4";
     /** 错误代码 */
    private static final String ERR_CODE = "404";

    @Async(value = "threadPoolTaskExecutor")
    @Override
    public void handle(Map map, String ipAddress) {
        GatherdataLog gl = (GatherdataLog) map.get("gl");
        // 更新数据库 使用的key
        String sessionId = gl.getSessionId();
        // 华东操作star
        String xml = xmlhdservice.createHdXml(map);
        String resXml = forwardService.callServices(xml, hdWsdl, hdNamespace, hdMethodName);

        // 将发送及响应的报文 保存入库
        XmlContent xmlContent = genXml(xml, sessionId, HD_XML_NAME);
        xmlContent.setXmlResponse(resXml);
        xmlContentMapper.insertXmlContent(xmlContent);
        JSONObject hdjson = null;
        try {
            hdjson = JSONObject.parseObject(XmlUtil.xmlToJson(resXml));

        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
        // 判断华东响应结果
        if (isForwardCustoms(hdjson, sessionId)) {
            String request = xmlzsService.createZsJson(map);
//             jsonStr = "404";
            GatherdataLog saveGl = new GatherdataLog();
            String hintInfo = "";

            String jsonStr = forwardService.callServices(request, zsWsdl, zsNamespace, zsMethodName);
            if (ERR_CODE.equals(jsonStr)) {
                /*总署交互异常*/
                saveGl.setTechErrorCode("Z404");
                saveGl.setTechErrorDescription("调用总署服务超时");
                saveGl.setCheckResult("N");
                hintInfo = "调用总署服务超时,不放行";
            } else {
                // 返回结果解析入库
                JSONObject json = JSONObject.parseObject(jsonStr);
                hintInfo = (String) json.get("hintInfo");
                String checkResult = (String) json.get("checkResult");
                switch (checkResult) {
                    case "0":
                        saveGl.setCheckResult("N");
                        break;
                    case "2":
                        saveGl.setCheckResult("M");
                        break;
                    default:
                        saveGl.setCheckResult("Y");
                }
            }
            //将与总署交互记录保存到数据库中
            XmlContent zsJson = genXml(request, sessionId, ZS_XML_NAME);
            zsJson.setXmlResponse(jsonStr);
            xmlContentMapper.insertXmlContent(zsJson);
            /*将记录保存到数据库*/
            saveGl.setOpHint(hintInfo);
            saveGl.setSessionId(sessionId);
            glMapper.updateGatherdataLog(saveGl);
        }
        sendCollect(sessionId, ipAddress);
    }

    @Override
    public void isCorrect(Map map) throws CustomException {

        StringBuilder str = new StringBuilder();
        GatherdataLog gl = (GatherdataLog) map.get("gl");
        List<ValidateResult> validate = MyAnnotation.validate(gl);
        for (ValidateResult va : validate) {
            if (va.isValid()) {
                str.append(va.getMessage()).append("\n");
            }
        }
        try {
            Double.valueOf(gl.getGrossWt());
        } catch (Exception e) {
            logger.error(e.getMessage());
            str.append("地磅称重的值不是数字");
        } finally {
            if (StringUtils.isNotEmpty(str.toString())) {
                throw new CustomException(str.toString());
            }

        }
    }

    @Override
    public void saveXml(XmlContent xmlContent) {
        xmlContentMapper.insertXmlContent(xmlContent);
    }

    @Override
    public XmlContent genXml(String xml, Map map, String xmlName) {
        XmlContent xmlContent = new XmlContent();
        // 获取 sessionId
        if (null != map) {
            GatherdataLog gl = (GatherdataLog) map.get("gl");
            if (StringUtils.isNotNull(gl)) {
                xmlContent.setSessionId(gl.getSessionId());
            }
        }
        xmlContent.setXmlName(xmlName);
        xmlContent.setXmlContent(xml);
        return xmlContent;
    }

    @Override
    public XmlContent genXml(String xml, String sessionId, String xmlName) {
        XmlContent xmlContent = new XmlContent();
        // 获取 sessionId
        xmlContent.setSessionId(sessionId);
        xmlContent.setXmlName(xmlName);
        xmlContent.setXmlContent(xml);
        return xmlContent;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void xmlToJavaBenSave(Map map) {
        int row = 0;
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        GatherdataLog gatherdataLog = (GatherdataLog) map.get("gl");
        String sessionId = gatherdataLog.getSessionId();
        try {
            for (Map.Entry<String, Object> entrty : entries) {
                if (entrty.getKey().contains("cr")) {
                    ContaRecognition contaRecognition = (ContaRecognition) entrty.getValue();
                    contaRecognition.setSessionId(sessionId);
                    row = contaRecognitionMapper.insertContaRecognition(contaRecognition);

                } else if (entrty.getKey().contains("gl")) {
                    row = glMapper.insertGatherdataLog(gatherdataLog);

                } else if (entrty.getKey().contains("dr")) {
                    DrRfid drRfid = (DrRfid) entrty.getValue();
                    drRfid.setSessionId(sessionId);
                    row = drRfidMapper.insertDrRfid(drRfid);

                } else if (entrty.getKey().contains("extend")) {
                    Extend extend = (Extend) entrty.getValue();
                    extend.setSessionId(sessionId);
                    row = extendMapper.insertExtend(extend);

                } else if (entrty.getKey().contains("fi")) {
                    FormInfo formInfo = (FormInfo) entrty.getValue();
                    formInfo.setSessionId(sessionId);
                    row = formInfoMapper.insertFormInfo(formInfo);

                } else if (entrty.getKey().contains("ic")) {
                    IcInfo icInfo = (IcInfo) entrty.getValue();
                    icInfo.setSessionId(sessionId);
                    row = icInfoMapper.insertIcInfo(icInfo);

                } else if (entrty.getKey().contains("photo")) {
                    Photo photo = (Photo) entrty.getValue();
                    photo.setSessionId(sessionId);
                    row = photoMapper.insertPhoto(photo);

                } else if (entrty.getKey().contains("vr")) {
                    VeLicenseRecognition veLicenseRecognition = (VeLicenseRecognition) entrty.getValue();
                    veLicenseRecognition.setSessionId(sessionId);
                    row = veLicenseRecognitionMapper.insertVeLicenseRecognition(veLicenseRecognition);
                } else if (entrty.getKey().contains("ve")) {
                    VeRfid veRfid = (VeRfid) entrty.getValue();
                    veRfid.setSessionId(sessionId);
                    row = gVeRfidMapper.insertGveRfid(veRfid);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    /**
     * 校验是否转发到总署
     */
    private boolean isForwardCustoms(JSONObject jsonObject, String sessionId) {
        GatherdataLog gl = new GatherdataLog();
        // 解析数据入库
        gl.setSessionId(sessionId);
        if (StringUtils.isNull(jsonObject) || jsonObject.isEmpty()) {
            gl.setTechErrorCode("H404");
            gl.setTechErrorDescription("调用沈阳物流平台服务超时");
            gl.setCheckResult("N");
            gl.setOpHint("调用沈阳关物流平台超时,不放行");
            glMapper.updateGatherdataLog(gl);
            return true;
        }
        JSONObject root = (JSONObject) jsonObject.get("MESSAGE_LIST_1.2");
        if (StringUtils.isNull(root)) {
            return true;
        }
        // 获取返回值
        String checkResult = (String) root.get("CHK_RESULT");
        gl.setCheckResult(checkResult);
        String opHint = (String) root.get("LED_INFO");
        gl.setOpHint(opHint);
        glMapper.updateGatherdataLog(gl);
        return false;
    }


    public Map createMap(String sessionId) {
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

    public void sendCollect(String sessionId, String ipAddress) {
        // 形成82报文发送给采集端
        // 从数据库中获取 Map
        Map allMap = createMap(sessionId);
        String xml82 = xmlCollectService.create82Xml(allMap);
        String wsdl = ipAddress + ctWsdl;
        GatherdataLog gl82 = new GatherdataLog();
        gl82.setSessionId(sessionId);
        String resJson = forwardService.callCollectServices(xml82, "xml82", wsdl, ctNamespace, ctMethodName);
        if (ERR_CODE.equals(resJson)) {
            gl82.setTechErrorCode("C404");
            gl82.setTechErrorDescription("采集端服务调用超时");
            resJson = "无法将放行结果返回给客户系统";
            glMapper.updateGatherdataLog(gl82);
        }
        // 将发送给采集端数据和响应结果保存到数据库中
        XmlContent xmlContent82 = genXml(xml82, sessionId, CT_XML_NAME);
        xmlContent82.setXmlResponse(resJson);
        xmlContentMapper.insertXmlContent(xmlContent82);
    }
}

