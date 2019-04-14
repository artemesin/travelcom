package common.solutions.utils.xml.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public final class XmlStaxUtils {

    private XmlStaxUtils() {
    }

    public static XMLStreamReader getReader(InputStream inputStream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLStreamReader(inputStream);
    }


    public static String readContentBetweenTags(XMLStreamReader reader) throws XMLStreamException {
        StringBuilder sb = new StringBuilder();

        while (reader.hasNext()){
            int eventType = reader.next();

            switch (eventType){
                case XMLStreamConstants
                        .CHARACTERS:
                case XMLStreamConstants.CDATA: {
                    sb.append(reader.getText());
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return sb.toString();
                }
            }

        }
        throw new RuntimeException("Wasn't suitable closing tag");
    }
}
