package com.lhzn.soft.framework.aspectj.enums;

public enum XmlStage {
    /**
     * 采集端上传数据  1
     */
    s_collection_81,
    /**
     * 采集端接受数据  2
     */
    r_collection_82,

    /**
     * 转发到总署数据  3
     */
     f_zs_json,
    /**
     * 接受总署数据   4
     */
    r_zs_json,
    /**
     * 转发到华东数据  5
     */
    f_hd_xml1,
    /**
     * 接受华东的放行数据 6
     */
    r_hd_xml2,
    /**
     * 转发放行数据   7
     */
    f_hd_xml3,
    /**
     * 接收执行结果  8
     */
    r_hd_result,
    /**
     * 转发总署的放行结果报文 9
     */
    f_hd_xml4,
    /**
     * 接受执行结果报文  10
     */
    r_hd_xml4_result,
}
