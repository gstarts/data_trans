package com.lhzn.soft.project.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 车辆通关箱号识别对象 g_conta_recognition
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
@Data
public class ContaRecognition implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 会话编号 */

    private String sessionId;

    /** 箱号 */

    private String contaId;

    /** 箱型20：20英尺40：40英尺45：45英尺 */

    private String contaType;

    /** 箱是否上锁y：上锁n：未上锁 */

    private String contaLock;

    /** 识别自信度y：自信n：不自信 */

    private String confidenceRatio;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sessionId", getSessionId())
            .append("contaId", getContaId())
            .append("contaType", getContaType())
            .append("contaLock", getContaLock())
            .append("confidenceRatio", getConfidenceRatio())
            .toString();
    }
}
