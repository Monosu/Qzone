package com.example.linxi.qzone.common;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by linxi on 2017/2/1.
 */

public class DomXml {
    /**
     * 根据传入的xml字符串解析成document
     */
    public static Document loadXml(String context){
        //获取单例
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Document document=null;
        DocumentBuilder documentBuilder=null;
        try {
            //通过documentBuilderFactory创建documentBuilder对象
            documentBuilder=factory.newDocumentBuilder();
            //创建输入源对象
            InputSource is=new InputSource();
            //设置转换流文件
            is.setCharacterStream(new StringReader(context));
            //解析成document对象
            document=documentBuilder.parse(is);
        } catch (Exception e) {
            //// TODO: handle exception
            e.printStackTrace();
        }
        return document;
    }
}
