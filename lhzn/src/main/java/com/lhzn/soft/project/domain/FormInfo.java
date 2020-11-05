package com.lhzn.soft.project.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 车辆通关时单证号信息对象 g_form_info
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
@Data
public class FormInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 会话编号 */

    private String sessionId;

    /** 单证号类型 */

    private String formType;

    /** 单证号 */

    private String formId;

    /** 主管海关 */

    private String masterCustomsCode;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sessionId", getSessionId())
            .append("formType", getFormType())
            .append("formId", getFormId())
            .append("masterCustomsCode", getMasterCustomsCode())
            .toString();
    }
}
