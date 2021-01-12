package com.lhzn.soft.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 *  场所管理对象 s_station_management
 * 
 * @author lanzhenyuan
 * @date 2020-11-16
 */
public class StationManagement implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 场所代码 */
    private String supvLoctCode;

    /** 通道编号 */
    private String chnlNo;

    /** 是否可用 0:禁用，1可用 */
    private String status;

    /** 对接系统 字典表 */
    private String docking;

    /** 场所名称 */
    private String stationName;

    /** 通道名称 */
    private String chnlName;

    /** 扩展 */
    private String expand;

    /** 扩展2 */
    private String expand2;

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
    public void setChnlNo(String chnlNo) 
    {
        this.chnlNo = chnlNo;
    }

    public String getChnlNo() 
    {
        return chnlNo;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDocking(String docking) 
    {
        this.docking = docking;
    }

    public String getDocking() 
    {
        return docking;
    }
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
    }
    public void setChnlName(String chnlName) 
    {
        this.chnlName = chnlName;
    }

    public String getChnlName() 
    {
        return chnlName;
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
            .append("chnlNo", getChnlNo())
            .append("status", getStatus())
            .append("docking", getDocking())
            .append("stationName", getStationName())
            .append("chnlName", getChnlName())
            .append("expand", getExpand())
            .append("expand2", getExpand2())
            .append("revision", getRevision())
            .toString();
    }
}
