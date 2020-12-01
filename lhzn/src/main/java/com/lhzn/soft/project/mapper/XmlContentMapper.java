package com.lhzn.soft.project.mapper;

import com.lhzn.soft.project.domain.XmlContent;

import java.util.List;

public interface XmlContentMapper {

    /**
     * 查询报文信息
     *
     * @param id 报文主键
     * @return  报文信息
     */
     XmlContent selectXmlContentById (Long id);

    /**
     * 查询交互的xml报文列表
     *
     * @param id sessionId
     * @return 报文列表集合
     */
     List<XmlContent> selectXmlContentBysessionId (String id);

    /**
     * 新增交互报文
     *
     * @param xml 报文信息
     * @return 结果
     */
     int insertXmlContent (XmlContent xml);

    /**
     * 修改交互报文信息
     *
     * @param xml 报文信息
     * @return 结果
     */
     int updateXmlContent (XmlContent xml);

}
