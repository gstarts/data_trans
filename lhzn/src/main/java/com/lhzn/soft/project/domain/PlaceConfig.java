package com.lhzn.soft.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 场所配置对象 place_config
 * 
 * @author lanzhenyuan
 * @date 2020-11-30
 */
public class PlaceConfig implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 场所代码 */
    private String supvLoctCode;

    /** 场所名称 */
    private String stationName;

    /** 扩展 */
    private String expand;

    /** 扩展2 */
    private String expand2;

    /** 网络地址 */
    private String ipAddress;

    /** 乐观锁 */
    private Long revision;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSupvLoctCode(String supvLoctCode) 
    {
        this.supvLoctCode = supvLoctCode;
    }

    public String getSupvLoctCode() 
    {
        return supvLoctCode;
    }
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
    }
    public void setExpand(String expand) 
    {
        this.expand = expand;
    }

    public String getExpand() 
    {
        return expand;
    }
    public void setExpand2(String expand2) 
    {
        this.expand2 = expand2;
    }

    public String getExpand2() 
    {
        return expand2;
    }
    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() 
    {
        return ipAddress;
    }
    public void setRevision(Long revision) 
    {
        this.revision = revision;
    }

    public Long getRevision() 
    {
        return revision;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("supvLoctCode", getSupvLoctCode())
            .append("stationName", getStationName())
            .append("expand", getExpand())
            .append("expand2", getExpand2())
            .append("ipAddress", getIpAddress())
            .append("revision", getRevision())
            .toString();
    }
}
