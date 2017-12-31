package com.github.tb.mybatispractice.xpath;

import org.apache.ibatis.io.Resources;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

/**
 * Created by tianbing on 2017/12/22.
 */
public class XPathTest {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        //开启验证
        documentBuilderFactory.setValidating(false);
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

        InputStream inputStream = Resources.getResourceAsStream("inventory.xml");
        Document document = builder.parse(inputStream);

        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();

        XPathExpression expression = xPath.compile("//inventory");
        Object object = expression.evaluate(document, XPathConstants.NODESET);
        System.out.println(object);
    }
}
