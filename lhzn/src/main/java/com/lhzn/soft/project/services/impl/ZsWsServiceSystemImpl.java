package com.lhzn.soft.project.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lhzn.soft.framework.aspectj.enums.Docking;
import com.lhzn.soft.project.domain.GatherdataLog;
import com.lhzn.soft.project.domain.IcInfo;
import com.lhzn.soft.project.domain.SendJson;
import com.lhzn.soft.project.domain.XmlContent;
import com.lhzn.soft.project.mapper.GatherdataLogMapper;
import com.lhzn.soft.project.mapper.XmlContentMapper;
import com.lhzn.soft.project.services.BusinessSystemService;
import com.lhzn.soft.project.services.ForwardService;
import com.lhzn.soft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 金二保税业务平台实现
 *
 * @author qishuai
 */
@Service("ZS")
public class ZsWsServiceSystemImpl implements BusinessSystemService {
    @Value("${webservice.zs.wsdl}")
    private String wsdl;
    @Value("${webservice.zs.name}")
    private String namespace;
    @Value("${webservice.zs.method}")
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
        SendJson sj = new SendJson();
        GatherdataLog gl = (GatherdataLog) map.get("gl");
        IcInfo ic = (IcInfo) map.get("ic");
        //必填
        sj.setWtGROSS(gl.getGrossWt());
        sj.setVeLicenseno(gl.getVeLicenseNo());
        sj.setRbpID(gl.getChnlNo());
        //进区还是出区
        sj.setIsEnter("");
        // 当前关区
        sj.setCusCode(gl.getCusCode());
        sj.setIoAreaFlag(gl.getIEFlag());
        // 卡口类型标识 0：单卡口  1：双卡口的1卡 2：双卡口的2卡
        sj.setRpbType(gl.getRpbType());
        sj.setVeRfid("");
        // 暂时没使用
        sj.setFormID("");
        sj.setFormType("");
        sj.setContainerNO("");
        sj.setIcID("");
        if (StringUtils.isNotNull(ic)) {
            sj.setIcNo(ic.getIcNo());
        } else {
            sj.setIcNo("");
        }
        sj.setIcType("");
        System.out.println("dom4j createZsJson success!");
        return JSON.toJSONString(sj);
    }

    @Override
    public String callServices(String reqXml) {
        return forwardService.callServices(reqXml, wsdl, namespace, methodName);
    }

    @Override
    public boolean isNotRelease(String resXml, String sessionId) {
        JSONObject json = JSONObject.parseObject(resXml);
        GatherdataLog gl = new GatherdataLog();
        gl.setSessionId(sessionId);
        String code = String.valueOf(json.get("code")) ;
        if (ERR_CODE.equals(code)) {
            gl.setCheckResult("N");
            gl.setOpHint("金二业务系统调用超时");
            glMapper.updateGatherdataLog(gl);
            return true;
        }
        String hintInfo = (String) json.get("hintInfo");
        String checkResult = (String) json.get("checkResult");
        switch (checkResult) {
            case "0":
                gl.setCheckResult("N");
                break;
            case "2":
                gl.setCheckResult("M");
                break;
            case "1":
                gl.setCheckResult("Y");
                break;
            default:
                break;
        }
        gl.setOpHint(hintInfo);
        glMapper.updateGatherdataLog(gl);
        return "N".equals(gl.getCheckResult());
    }

    @Override
    public void saveInteractiveXml(String reqXml, String resXml, String sessionId) {
        XmlContent xmlContent = new XmlContent();
        xmlContent.setSessionId(sessionId);
        xmlContent.setXmlName(Docking.ZS.name());
        xmlContent.setXmlContent(reqXml);
        xmlContent.setXmlResponse(resXml);
        xmlContentMapper.insertXmlContent(xmlContent);
    }
}
