package co.edu.escuelaing.arem.bpmndoc;

import co.edu.escuelaing.arem.bpmndoc.model.Pool;
import co.edu.escuelaing.arem.bpmndoc.parser.BPMNParseException;
import co.edu.escuelaing.arem.bpmndoc.parser.XMLParser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;

/**
 * Main class of BPMNDoc which organizes the two main stages of Documentation generation (Parsing and HTML Generation), and gives the final user interaction.
 * @author Daniel Ospina
 */
public class Main {
    
    /**
     * Main method
     * @param args name of the .bpmn file
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
        }
        else {
            String filename = args[0]; 
            System.out.println("Parsing started for: " + args[0]);
            Node node = startParse(filename);
            if (node != null) {
                Pool pool = ModelBuilder.getModel(node);
                generateHTML(pool);
                System.out.println("Parsing finished, BPMN Documentation generated at: " + HTMLBuilder.GENERATION_PATH);   
            }
        }
    }
    
    /**
     * Invokes the XML parser and gets the Node representation of the XMI File.
     * @param filename name of the XML file.
     * @return a Node Representation of the XMI Tree in the file.
     */
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
    
    /**
     * Shows the user how to use the program
     */
    private static void usage() {
        System.out.println("Use java -jar [BPMN_JAR_NAME] [.bpmn file]");
    }
    
    /**
     * Invokes the BPMN HTML Generator for a given Pool.
     * @param pool Pool Object to generate documentation for.
     */
    private static void generateHTML(Pool pool) {
        try {
            HTMLBuilder.generateHtml(pool);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error generating html file", ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error generating html file", ex);
        }
    }
}
