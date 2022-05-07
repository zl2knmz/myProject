package com.cloud.util;

import lombok.extern.slf4j.Slf4j;
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

    public static void main(String[] args) {
        String xml = "<Setting><IsAdmin>True</IsAdmin><PlaybackMemberSN>1,2</PlaybackMemberSN></Setting>";
        String nodeName = "PlaybackMemberSN";
        String value = getXmlNodeValue(xml, nodeName);
        System.out.println(value);

        String xml1 = "<Setting><IsAdmin>True</IsAdmin></Setting>";
        String nodeName1 = "PlaybackMemberSN";
        String value1 = "1";
        System.out.println(updateXmlNode(xml1, nodeName1, value1));
    }
}
