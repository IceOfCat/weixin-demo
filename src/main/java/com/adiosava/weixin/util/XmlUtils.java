package com.adiosava.weixin.util;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yuxudong
 * @Date 2019/9/23 16:51
 * @Version 1.0
 **/
@Slf4j
public abstract class XmlUtils {
    private static Map<Class<?>, JAXBContext> JAXB_CONTEXT_MAP = new ConcurrentHashMap(256);

    public XmlUtils() {
    }

    public static <T> T convertToObject(Class<T> clazz, String xml) {
        return convertToObject(clazz, (new StringReader(xml)));
    }

    public static <T> T convertToObject(Class<T> clazz, InputStream inputStream) {
        return convertToObject(clazz, (new InputStreamReader(inputStream)));
    }

    public static <T> T convertToObject(Class<T> clazz, InputStream inputStream, Charset charset) {
        return convertToObject(clazz, (new InputStreamReader(inputStream, charset)));
    }

    public static <T> T convertToObject(Class<T> clazz, Reader reader) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            if (!JAXB_CONTEXT_MAP.containsKey(clazz)) {
                JAXB_CONTEXT_MAP.put(clazz, JAXBContext.newInstance(clazz));
            }

            Source xmlSource = new SAXSource(spf.newSAXParser().getXMLReader(), new InputSource(reader));
            Unmarshaller unmarshaller = JAXB_CONTEXT_MAP.get(clazz).createUnmarshaller();
            return (T) unmarshaller.unmarshal(xmlSource);
        } catch (Exception var5) {
            log.error("", var5);
            return null;
        }
    }

    public static String convertToXML(Object object) {
        try {
            if (!JAXB_CONTEXT_MAP.containsKey(object.getClass())) {
                JAXB_CONTEXT_MAP.put(object.getClass(), JAXBContext.newInstance(object.getClass()));
            }

            Marshaller marshaller = JAXB_CONTEXT_MAP.get(object.getClass()).createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", true);
            marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
                @Override
                public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException {
                    writer.write(ac, i, j);
                }
            });
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(object, stringWriter);
            return stringWriter.getBuffer().toString();
        } catch (Exception var3) {
            log.error("", var3);
            return null;
        }
    }

    public static Map<String, String> convertToMap(String xml) {
        LinkedHashMap map = new LinkedHashMap();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            dbf.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            dbf.setXIncludeAware(false);
            dbf.setExpandEntityReferences(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xml);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);
            Element root = document.getDocumentElement();
            if (root != null) {
                NodeList childNodes = root.getChildNodes();
                if (childNodes != null && childNodes.getLength() > 0) {
                    for(int i = 0; i < childNodes.getLength(); ++i) {
                        Node node = childNodes.item(i);
                        if (node != null && node.getNodeType() == 1) {
                            map.put(node.getNodeName(), node.getTextContent());
                        }
                    }
                }
            }
        } catch (Exception var11) {
            log.error("", var11);
        }

        return map;
    }
}
