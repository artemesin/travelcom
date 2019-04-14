package common.solutions.utils.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public final class XmlDomUtils {

    private XmlDomUtils() {
    }

    public static Document getDocument(String file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(file));
    }

    public static Element getOnlyElement(Document document, String tagName) {
        return (Element) document.getElementsByTagName(tagName).item(0);
    }

    public static String getOnlyElementTextContentOrNull(Element elementSrc, String tagName) {
        NodeList elementsByTagName = elementSrc.getElementsByTagName(tagName);
        if(elementsByTagName.getLength() > 0) {
            return elementsByTagName.item(0).getTextContent();
        }else {
            return null;
        }
    }


    public static String getOnlyElementTextContent(Element elementSrc, String tagName) {
        NodeList elementsByTagName = elementSrc.getElementsByTagName(tagName);

        return elementsByTagName.item(0).getTextContent();
    }

    public static Element getOnlyElement(Element elementSrc, String tagName){
        return (Element) elementSrc.getElementsByTagName(tagName).item(0);
    }
}
