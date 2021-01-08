package com.lhzn.soft.utils;

import com.lhzn.soft.framework.aspectj.enums.Docking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpUtilsTest {
@Resource

    @Test
   public void test(){
//        boolean b = HttpUtils.urlIsReach("http://192.168.11.188:8088/webServices/parseXml?wsdl");
//        System.out.println(b);

        System.out.println(Docking.YBTKJ.name());
        System.out.println(Docking.HDFZ);
        List<String> strings = StringUtils.str2List("4555     ,78787", ",", true, true);

        for (int i=0;i<=strings.size();i++){
            System.out.println(strings.get(i));
        }
        if(Docking.HDFZ.name().equals("HDFZ")){
            System.out.println("对接华东辅助");
        }



    }




}