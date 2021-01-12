package com.lhzn.soft.project.mapper;

import com.lhzn.soft.utils.StringUtils;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StationManagementMapperTest extends TestCase {

    @Resource
    private StationManagementMapper mapper;
    @Test
    public void testGetDockingSystem() {
        String dockingSystem = mapper.getDockingSystem("22222");
        List<String> strings = StringUtils.str2List(dockingSystem, ",", true, true);
        for (String s:strings) {
            System.out.println(s);
        }

        System.out.println(dockingSystem);
    }
}