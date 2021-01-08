package com.lhzn.soft.utils;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.io.StringWriter;

import static org.dom4j.DocumentHelper.parseText;

/**
 * xml 工具类
 *
 * @author gstarqs
 */
public class XmlUtil {

    /**
     * 字符串格式的转换为xml文档
     *
     * @param str 字符串
     * @return 结果
     * @throws DocumentException 异常
     */
    public static Document stringToXml ( String str ) throws DocumentException {
        return parseText (str);
    }

    /**
     * xml转换 json
     *
     * @param xml 字符串类型的xml
     * @return json
     */
    public static String xmlToJson ( String xml ) throws JSONException {
        String str1 = xml.replace ("GB2312", "UTF-8");
        JSONObject js = XML.toJSONObject (str1);
        System.out.println ("得到的json" + js.toString ());
        return js.toString ();
    }

    /**
     *  将xml转化成字符串
     * @param doc xml
     * @return 结果
     */
    public static String Output(Document doc) {
        OutputFormat oFormat = OutputFormat.createPrettyPrint();
        oFormat.setEncoding("UTF-8");
        StringWriter sWriter = new StringWriter();
        XMLWriter xWriter = new XMLWriter(sWriter, oFormat);
        try {
            xWriter.write(doc);
            xWriter.flush();
            xWriter.close();
        } catch (IOException e) {
            System.err.println("转换xml异常！");
        }
       return  sWriter.toString();
    }
}
