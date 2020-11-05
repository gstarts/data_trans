package com.lhzn.soft.project.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回信息结果类
 */
@Data
public class ReleaseResult implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 报文唯一序列号
     */
    private String seqNo;
    /**
     * 场站代码
     */
    private String areaId;
    /**
     * 卡口代码
     */
    private String chnlNo;
    /**
     * 进出标志
     */

    private String iEType;
    /**
     * 放行指令
     */
    private String checkResult;
    /**
     * LED提示信息
     */
    private String hintInfo;
    /**
     * 时间
     */
    private String fileTime;
    /**
     * IC卡号
     */
    private String drIcNo;
    /**
     * 关锁号码
     */
    private String esealId;
    /**
     * 关锁密钥
     */
    private String esealKey;
    /**
     * 施解封指令
     */
    private String esealResult;
    /**
     * 核放单证号码
     */
    private String formId;
    /**
     * 车牌号
     */
    private String veName;
    /**
     * GPS ID号
     */
    private String gpsId;
    /**
     * 原关区代码
     */
    private String originCustoms;
    /**
     * 目的地关区代码
     */
    private String destCustoms;
    /**
     * GPS监控指令
     */
    private String gpsResult;
    /**
     * 收发卡指令
     */
    private String icResult;
}
