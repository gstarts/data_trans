package com.lhzn.soft.project.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 总署数据实体
 * @author gstarqs
 */
@Data
public class SendJson implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 卡口类型标识
     */
    @JSONField(name = "rpbType")
    private String rpbType;
    /**
     * 卡全球唯一标识
     */
    @JSONField(name = "iC_ID")
    private String icID;
    /**
     * IC卡编码
     */
    @JSONField(name = "iC_NO")
    private String icNo;
    /**
     * IC卡类型
     */
    @JSONField(name = "iC_TYPE")
    private String icType;
    /**
     * 车辆电子车牌号
     */
    @JSONField(name = "vE_RFID")
    private String veRfid;
    /**
     * 物理车牌号
     */
    @JSONField(name = "vE_LICENSENO")
    private String veLicenseno;
    /**
     * 单证类型
     */
    @JSONField(name = "form_TYPE")
    private String formType;
    /**
     * 单证编号
     */
    @JSONField(name = "form_ID")
    private String formID;
    /**
     * 集装箱编号
     */
    @JSONField(name = "Container_NO")
    private String containerNO;
    /**
     * 地磅重量
     */
    @JSONField(name = "wT_GROSS")
    private String wtGROSS;
    /**
     * 物理卡口标识
     */
    @JSONField(name = "RbpID")
    private String rbpID;
    /**
     * 进去还是出区
     */
    @JSONField(name = "isEnter")
    private String isEnter;
    /**
     * 当前关区号
     */
    @JSONField(name = "customsCode")
    private String cusCode;
    /**
     * 进出标识
     */
    @JSONField(name = "ioAreaFlag")
    private String ioAreaFlag;


}
