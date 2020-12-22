package com.lhzn.soft.project.services.impl;

import com.alibaba.fastjson.JSON;
import com.lhzn.soft.project.domain.GatherdataLog;
import com.lhzn.soft.project.domain.IcInfo;
import com.lhzn.soft.project.domain.SendJson;
import com.lhzn.soft.project.services.XmlZsService;
import com.lhzn.soft.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 总署报文处理类
 *
 * @author qishuai
 */
@Service
public class XmlCustomsServiceImpl implements XmlZsService {

    @Override
    public String createZsJson ( Map map ) {
        SendJson sj = new SendJson ();
        GatherdataLog gl = (GatherdataLog) map.get ("gl");
        IcInfo ic = (IcInfo) map.get ("ic");
        System.out.println ("IC卡对象"+ ic);
        //必填
        sj.setWtGROSS (gl.getGrossWt ());
        sj.setVeLicenseno (gl.getVeLicenseNo ());
        sj.setRbpID (gl.getChnlNo ());
        //进区还是出区
        sj.setIsEnter ("");
        // 当前关区
        sj.setCusCode (gl.getCusCode ());
        sj.setIoAreaFlag (gl.getIEFlag ());
        // 卡口类型标识 0：单卡口  1：双卡口的1卡 2：双卡口的2卡
        sj.setRpbType (gl.getRpbType ());
        sj.setVeRfid ("");
        // 暂时没使用
        sj.setFormID ("");
        sj.setFormType ("");
        sj.setContainerNO ("");
        sj.setIcID ("");
        if (StringUtils.isNotNull (ic)) {
            sj.setIcNo (ic.getIcNo ());

        } else {
            sj.setIcNo ("");

        }
        sj.setIcType ("");
        System.out.println ("dom4j createZsJson success!");
        return JSON.toJSONString (sj);
    }
}
