/**
 * Created by lszlawrence on 2016/12/13.
 */
        import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;


public class XmlUtils {

    public String buildPlaceXML(image subscription) {
        String xmlDoc = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        try {
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement("subscription");
            this.addAttribute(doc, rootElement, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            doc.appendChild(rootElement);

            Element placeNode = doc.createElement("photos");
            addChildElement(doc, placeNode, "photosId", subscription.getId().toString());
            addChildElement(doc, placeNode, "userId", Integer.toString(subscription.getUid()));
            addChildElement(doc, placeNode, "date", subscription.getDate());
            addChildElement(doc, placeNode, "address", subscription.getAddress());
            addChildElement(doc, placeNode, "category", subscription.getCategory());
            addChildElement(doc, placeNode, "introMessage", subscription.getIntroMessage());
            addChildElement(doc, placeNode, "recentComment", subscription.getRecentComment());
            addChildElement(doc, placeNode, "devices", subscription.getDevices());
            addChildElement(doc, placeNode, "filters", subscription.getFilters());
            addChildElement(doc, placeNode, "lens", subscription.getLens());
            // addChildElement(doc, placeNode, "stories", stories);

            rootElement.appendChild(placeNode);

            System.out.println("buildXML: rootElement" + rootElement);

            try {
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(doc), new StreamResult(writer));
                xmlDoc = writer.getBuffer().toString().replaceAll("\n|\r", "");
                xmlDoc = writer.getBuffer().toString();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        try {
            this.validate(xmlDoc, "src/main/java/subscription.xsd");
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return xmlDoc;
    }

    private void addAttribute(Document doc, Element targetElement, String attrName, String attrValue) {
        Attr attr = doc.createAttribute(attrName);
        attr.setValue(attrValue);
        targetElement.setAttributeNode(attr);
    }

    private void addChildElement(Document doc, Element targetElement, String tagName, String tagValue) {
        Element tag = doc.createElement(tagName);
        tag.appendChild(doc.createTextNode(tagValue));
        targetElement.appendChild(tag);
    }

    private boolean validate(String xmlDoc, String schemaPath) throws SAXException {

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(new File(schemaPath));
        Source xmlFile = new StreamSource(new StringReader(xmlDoc));
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();

        try {
            validator.validate(xmlFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}