package co.edu.escuelaing.arem.bpmndoc.parser;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
}
