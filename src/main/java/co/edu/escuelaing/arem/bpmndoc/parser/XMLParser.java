package co.edu.escuelaing.arem.bpmndoc.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * TODO
 * @author Daniel Ospina
 */
public class XMLParser {

    public static Node parse(InputStream in) throws BPMNParseException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new BPMNParseException("Error trying to parse...", ex);
        }
        Document doc;
        try {
            doc = db.parse(in);
        } catch (SAXException | IOException ex) {
            throw new BPMNParseException("Error trying to parse...", ex);
        }
        return doc;
    }

    public static void navigate(Node n) {
        navigate(n, "");
    }

    private static void navigate(Node n, String prefix) {
        System.out.println(prefix + "Node name : " + n.getNodeName());
        //System.out.println(prefix + "Node type : " + getNodeTypeName(n.getNodeType()));
        System.out.println(prefix + "Node value: " + n.getNodeValue());

        // Navegar los atributos del nodo
        NamedNodeMap childAttributes = n.getAttributes();
        if (childAttributes != null) {
            for (int i = 0; i < childAttributes.getLength(); i++) {
                navigate(childAttributes.item(i), prefix + "|a----");
            }
        }
        
        //Navegar los nodos hijo del nodo actual
        NodeList childnodes = n.getChildNodes();
        for (int i = 0; i < childnodes.getLength(); i++) {
            navigate(childnodes.item(i), prefix + "|-----");
        }
    }

    private static String getNodeTypeName(short nodeType) {
        String respuesta = "";
        switch (nodeType) {
            case Node.ATTRIBUTE_NODE:
                respuesta = "ATTRIBUTE_NODE";
                break;
            case Node.CDATA_SECTION_NODE:
                respuesta = "CDATA_SECTION_NODE";
                break;
            case Node.COMMENT_NODE:
                respuesta = "COMMENT_NODE";
                break;
            case Node.DOCUMENT_FRAGMENT_NODE:
                respuesta = "DOCUMENT_FRAGMENT_NODE";
                break;
            case Node.DOCUMENT_NODE:
                respuesta = "DOCUMENT_NODE";
                break;
            case Node.DOCUMENT_POSITION_CONTAINED_BY:
                respuesta = "DOCUMENT_POSITION_CONTAINED_BY";
                break;
            case Node.DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC:
                respuesta = "DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC";
                break;
            case Node.DOCUMENT_TYPE_NODE:
                respuesta = "DOCUMENT_TYPE_NODE";
                break;
            case Node.ELEMENT_NODE:
                respuesta = "ELEMENT_NODE";
                break;
            case Node.ENTITY_NODE:
                respuesta = "ENTITY_NODE";
                break;
            case Node.TEXT_NODE:
                respuesta = "TEXT_NODE";
                break;
            default:
                respuesta = "other type";
                break;
        }

        return respuesta;
    }
}
