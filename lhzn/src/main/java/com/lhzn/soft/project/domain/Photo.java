package com.lhzn.soft.project.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 车辆通关图片信息对象 g_photo
 * 
 * @author lanzhenyuan
 * @date 2020-06-18
 */
@Data
public class Photo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 会话编号 */

    private String sessionId;

    /** 唯一编号1、箱号图片：存储箱号图片名称前缀（不包含文件扩展名），如：09060070018001120003_20181119152430686502_;2、车牌图片：存储车牌图片名称，如：辽b9897h_2018-11-19_15-17-00.jpg */
    private String photoGuid;

    /** 图像视角1、箱号图片：conta;2、车牌图片：licenseplate */
    private String photoPerspective;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sessionId", getSessionId())
            .append("photoGuid", getPhotoGuid())
            .append("photoPerspective", getPhotoPerspective())
            .toString();
    }
}
