package com.lhzn.soft.project.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 车辆通关司机卡信息对象 g_dr_rfid
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
@Data
public class DrRfid implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 会话编号 */

    private String sessionId;

    /** 电子司机卡号 */

    private String rfidId;

    /** 司机姓名 */

    private String drName;

    /** 司机海关编号 */

    private String drCustomsNo;

    /** 车辆所属公司 */

    private String drCompany;

    /** 单位时间读卡次数 */

    private String drPerformance;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sessionId", getSessionId())
            .append("rfidId", getRfidId())
            .append("drName", getDrName())
            .append("drCustomsNo", getDrCustomsNo())
            .append("drCompany", getDrCompany())
            .append("drPerformance", getDrPerformance())
            .toString();
    }
}
