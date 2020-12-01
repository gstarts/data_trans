package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.project.domain.XmlContent;
import com.lhzn.soft.project.mapper.XmlContentMapper;
import com.lhzn.soft.project.services.XmlContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class XmlContentServiceImpl  implements XmlContentService {

    @Resource
    private XmlContentMapper xmlContentMapper;
    @Override
    public XmlContent selectXmlContentById(Long id) {
        return xmlContentMapper.selectXmlContentById(id);
    }
}
