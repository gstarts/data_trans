package com.lhzn.soft.project.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 车辆通关扩展字段对象 g_extend
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
@Data
public class Extend implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 会话编号 */

    private String sessionId;

    /** 字段名 */

    private String name;

    /** 字段值 */

    private String value;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sessionId", getSessionId())
            .append("name", getName())
            .append("value", getValue())
            .toString();
    }
}
