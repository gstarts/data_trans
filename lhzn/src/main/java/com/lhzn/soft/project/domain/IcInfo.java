package com.lhzn.soft.project.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 车辆通关信息中ic卡信息对象 g_ic_info
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
@Data
public class IcInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 会话编号 */

    private String sessionId;

    /** ic卡id号 */

    private String icId;

    /** ic卡号 */

    private String icNo;

    /** ic卡类型 */

    private String icType;

    /** 结构体 */

    private String icExtendedContent;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sessionId", getSessionId())
            .append("icId", getIcId())
            .append("icNo", getIcNo())
            .append("icType", getIcType())
            .append("icExtendedContent", getIcExtendedContent())
            .toString();
    }
}
