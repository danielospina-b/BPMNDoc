package co.edu.escuelaing.arem.bpmndoc.parser;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * TODO
 * @author Daniel Ospina
 */
public class XMLParser {
    
    //AGREGADO CODIGO DE EJEMPLO PARA ENTENDER FUNCIONAMIENTO
    
    static final String outputEncoding = "UTF-8";

    private static void usage() {
        System.out.println("Usage: xmlechoparser file1");
    }

    public static void main(String[] args) throws Exception {
        String filename = "MyDiagram1.bpmn";

        // Retire el false para hacer un programa que se innvoque desde la linea de comandos
        if (args.length < 1 && false) {
            usage();
        } else {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //filename = args[0]; 
            Document doc = db.parse(new File(filename));
            navigate(doc);
        }
    }

    private static void navigate(Node n) {
        navigate(n, "");
    }

    private static void navigate(Node n, String prefix) {
        System.out.println(prefix + "Node name : " + n.getNodeName());
        System.out.println(prefix + "Node type : " + getNodeTypeName(n.getNodeType()));
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
