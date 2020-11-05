package com.lhzn.soft.project.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 车辆通关时电子车牌信息对象 g_ve_rfid
 *
 * @author qishuai
 * @date 2020-06-20
 */
@Data
public class VeRfid implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 会话编号
     */

    private String sessionId;

    /**
     * 电子车牌标签号
     */

    private String rfidId;

    /**
     * 电子车牌车牌号
     */

    private String veLicenseNo;

    /**
     * 海关车辆编号
     */
    private String veCustomsNo;

    /**
     * 车辆称重
     */
    private String veWt;

    /**
     * 车辆所属公司
     */
    private String veCompany;

    /**
     * 单位时间读卡次数
     */
    private String vePerformance;

    /**
     * 境外或港澳车牌号
     */
    private String veLicenseNo2;

    /**
     * 托架号
     */
    private String trailerId;

    /**
     * 地磅误差
     */
    private String weightDiff;

    /**
     * 地磅称重
     */
    private String channelWeight;

    /**
     * 车辆理论总重量
     */
    private String grossWeight;

    /**
     * 车辆自重
     */
    private String vehicleWeight;

    /**
     * 司机海关编号
     */
    private String drCustomsNo;

    /**
     * 司机姓名
     */
    private String drName;

    @Override
    public String toString () {
        return new ToStringBuilder (this, ToStringStyle.MULTI_LINE_STYLE)
                .append ("id", getId ())
                .append ("sessionId", getSessionId ())
                .append ("rfidId", getRfidId ())
                .append ("veLicenseNo", getVeLicenseNo ())
                .append ("veCustomsNo", getVeCustomsNo ())
                .append ("veWt", getVeWt ())
                .append ("veCompany", getVeCompany ())
                .append ("vePerformance", getVePerformance ())
                .append ("veLicenseNo2", getVeLicenseNo2 ())
                .append ("trailerId", getTrailerId ())
                .append ("weightDiff", getWeightDiff ())
                .append ("channelWeight", getChannelWeight ())
                .append ("grossWeight", getGrossWeight ())
                .append ("vehicleWeight", getVehicleWeight ())
                .append ("drCustomsNo", getDrCustomsNo ())
                .append ("drName", getDrName ())
                .toString ();
    }
}