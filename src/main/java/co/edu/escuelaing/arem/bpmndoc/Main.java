package co.edu.escuelaing.arem.bpmndoc;

import co.edu.escuelaing.arem.bpmndoc.model.Pool;
import co.edu.escuelaing.arem.bpmndoc.parser.BPMNParseException;
import co.edu.escuelaing.arem.bpmndoc.parser.XMLParser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;

/**
 * TODO
 * @author Daniel Ospina
 */
public class Main {
    
    public static void main(String[] args) {
        String filename = args[0];        
        if (args.length < 1) {
            usage();
        }
        else {
            System.out.println("Parsing started for: " + args[0]);
            Node node = startParse(filename);
            if (node != null) {
                Pool pool = ModelBuilder.getModel(node);
                generateHTML(pool);
                System.out.println("Parsing finished, BPMN Documentation generated at: " + HTMLBuilder.GENERATION_PATH);   
            }
        }
    }
    
    public static Node startParse(String filename) {
        Node node = null;
        try {
            node = XMLParser.parse(new FileInputStream(filename));
        } catch (FileNotFoundException ex) {
            System.out.println("Error parsing " + filename + " | File not Found");
        } catch (BPMNParseException ex) {
            System.out.println("Error parsing" + filename);
        }
        return node;
    }
    
    private static void usage() {
        System.out.println("Use java -jar [BPMN_JAR_NAME] [.bpmn file]");
    }

    private static void generateHTML(Pool pool) {
        try {
            HTMLBuilder.generateHtml(pool);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error generating html file", ex);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error generating html file", ex);
        }
    }
}
