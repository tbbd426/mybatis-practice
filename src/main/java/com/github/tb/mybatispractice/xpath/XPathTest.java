package com.github.tb.mybatispractice.xpath;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * Created by tianbing on 2017/12/22.
 */
public class XPathTest {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        //开启验证
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(false);
        documentBuilderFactory.setCoalescing(false);
        documentBuilderFactory.setExpandEntityReferences(true);

        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("warn:" + exception);
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println("error:" + exception);
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("fatalError:" + exception);
            }
        });

        InputStream inputStream = XPathTest.class.getResourceAsStream("inventory.xml");
        Document document = builder.parse(inputStream);

    }
}
