package com.cloud.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author zl
 * @date 2022/4/20 10:45
 */
@Slf4j
public class XmlUtils {

    public static String getXmlNodeValue(String xml, String nodeName) {
        String value = null;
        if (StringUtils.isBlank(xml)) {
            return value;
        }
        // 把文件解析成document树
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
            // 获取根节点
            Element root = document.getRootElement();
            if (null != root.element(nodeName)) {
                value = root.element(nodeName).getText();
            }
        } catch (DocumentException e) {
            log.error("getXmlNodeValue", e);
        }
        return value;
    }

    public static String updateXmlNode(String xml, String nodeName, String value) {
        if (null == value) {
            value = "";
        }
        if (StringUtils.isBlank(xml)) {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("Setting");
            Element first = root.addElement(nodeName);
            first.setText(value);
            xml = document.getRootElement().asXML();
            return xml;
        } else {
            // 把文件解析成document树
            Document document = null;
            try {
                document = DocumentHelper.parseText(xml);
                // 获取根节点
                Element root = document.getRootElement();
                if (null != root.element(nodeName)) {
                    root.element(nodeName).setText(value);
                } else {
                    Element first = root.addElement(nodeName);
                    first.setText(value);
                }
                xml = document.getRootElement().asXML();
                return xml;
            } catch (DocumentException e) {
                log.error("updateXmlNode", e);
            }
        }
        return xml;
    }

    /**
     * @param value <![CDATA[内容]]>
     */
    public static String updateXmlNodeCDATA(String xml, String nodeName, String value) {
        if (null == value) {
            value = "";
        }
        if (StringUtils.isBlank(xml)) {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("Setting");
            Element first = root.addElement(nodeName);
            first.addCDATA(value);
            xml = document.getRootElement().asXML();
            return xml;
        } else {
            // 把文件解析成document树
            Document document = null;
            try {
                document = DocumentHelper.parseText(xml);
                // 获取根节点
                Element root = document.getRootElement();
                if (null != root.element(nodeName)) {
                    root.element(nodeName).setText("");
                    root.element(nodeName).addCDATA(value);
                } else {
                    Element first = root.addElement(nodeName);
                    first.setText("");
                    first.addCDATA(value);
                }
                xml = document.getRootElement().asXML();
                return xml;
            } catch (DocumentException e) {
                log.error("updateXmlNode", e);
            }
        }
        return xml;
    }

    public static void main(String[] args) {
        String xml = "<Setting><IsAdmin>True</IsAdmin><PlaybackMemberSN>1,2</PlaybackMemberSN><HdxTags><![CDATA[IT互联网,创业]]></HdxTags></Setting>";
        String nodeName = "HdxTags";
        String value = getXmlNodeValue(xml, nodeName);
        System.out.println(value);

        String xml1 = "<Setting><IsAdmin>True</IsAdmin></Setting>";
        String nodeName1 = "HdxTags";
        String value1 = "IT互联网--,创业--";
        String xml2 = updateXmlNodeCDATA(xml1, nodeName1, value1);
        System.out.println(xml2);

//        String xml3 = "<Setting><IsAdmin>True</IsAdmin><HdxTags><![CDATA[IT互联网--,创业--]]></HdxTags></Setting>";
        String nodeName2 = "HdxTags";
        String value2 = getXmlNodeValue(xml2, nodeName2);
        System.out.println(value2);
    }

}
