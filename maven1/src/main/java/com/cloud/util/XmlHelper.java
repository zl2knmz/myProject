package com.cloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(XmlHelper.class);

    private static DocumentBuilder dbBuilder = null;

    static {
        try {
            dbBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Document getDocument(String xml) {
        Document doc = null;
        synchronized (dbBuilder) {
            if (xml != null && xml.length() > 0) {
                try {
                    Reader rr = new StringReader(xml);
                    doc = dbBuilder.parse(new InputSource(rr));
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }

            }
        }
        return doc;
    }

    public static List<String> getXmlValueByName(String xml, String name) {
        synchronized (dbBuilder) {
            List<String> result = new ArrayList<String>();
            try {
                if (xml != null && xml.length() > 0) {
                    Document doc = null;
                    Reader rr = new StringReader(xml);
                    doc = dbBuilder.parse(new InputSource(rr));

                    NodeList node_list = doc.getElementsByTagName(name);
                    for (int i = 0; i < node_list.getLength(); i++) {
                        result.add(node_list.item(i).getFirstChild().getNodeValue());
                    }
                    rr.close();
                }

            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                try {
                    dbBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                } catch (ParserConfigurationException e1) {
                    // TODO Auto-generated catch block
                    LOGGER.error(e.getMessage(), e1);
                }

            }
            return result;
        }

    }
}
