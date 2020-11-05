package com.lhzn.soft.project.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * qwe对象 g_ve_license_recognition
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
@Data
public class VeLicenseRecognition implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 会话编号 */
    private String sessionId;

    /** 国内车牌号 */
    private String domesticLicenseNo;

    /** 国内车牌颜色 */
    private String domesticLicenseColor;

    /** 境外车牌号 */
    private String foreignLicenseNo;

    /** 境外车牌颜色 */
    private String foreignLicenseColor;

    /** 识别自信度y：自信n：不自信 */
    private String confidenceRatio;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sessionId", getSessionId())
            .append("domesticLicenseNo", getDomesticLicenseNo())
            .append("domesticLicenseColor", getDomesticLicenseColor())
            .append("foreignLicenseNo", getForeignLicenseNo())
            .append("foreignLicenseColor", getForeignLicenseColor())
            .append("confidenceRatio", getConfidenceRatio())
            .toString();
    }
}
