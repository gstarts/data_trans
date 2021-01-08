package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.project.domain.GatherdataLog;
import com.lhzn.soft.project.domain.IcInfo;
import com.lhzn.soft.project.domain.VeRfid;
import com.lhzn.soft.project.mapper.GatherdataLogMapper;
import com.lhzn.soft.project.mapper.GveRfidMapper;
import com.lhzn.soft.project.mapper.IcInfoMapper;
import com.lhzn.soft.project.services.XmlCollectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@SpringBootTest
@RunWith(SpringRunner.class)
public class XmlCollectServiceImplTest {

    @Resource
    private XmlCollectService service;
    @Resource
    private GatherdataLogMapper mapper;
    @Resource
    private IcInfoMapper infoMapper;
    @Resource
    private GveRfidMapper gveRfidMapper;
    @Test
    public void create82Xml() {
        GatherdataLog gatherdataLog = mapper.selectGatherdataLogById("20200601103146");
        IcInfo icInfo = infoMapper.selectIcInfo("20200601103146");
        VeRfid veRfid = gveRfidMapper.selectGveRfid("20200601103146");

        Map map = new HashMap();
        map.put("gl", gatherdataLog);
        map.put("ic", icInfo);
        map.put("ve", veRfid);
        String xml = service.create82Xml(map);

        System.out.println("生成的报文\n"+xml);
    }
}