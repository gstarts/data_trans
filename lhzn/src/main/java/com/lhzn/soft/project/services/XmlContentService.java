package com.lhzn.soft.project.services;

import com.lhzn.soft.project.domain.XmlContent;

/**
 * 报文信息接口
 * @author gstar
 */
public interface XmlContentService {
    /**
     * 查询报文信息
     *
     * @param id 报文主键
     * @return  报文信息
     */
    XmlContent selectXmlContentById (Long id);
}
