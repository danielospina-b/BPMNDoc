package co.edu.escuelaing.arem.bpmndoc;

import co.edu.escuelaing.arem.bpmndoc.model.Element;
import co.edu.escuelaing.arem.bpmndoc.model.Lane;
import co.edu.escuelaing.arem.bpmndoc.model.Pool;
import co.edu.escuelaing.arem.bpmndoc.parser.BPMNParseException;
import co.edu.escuelaing.arem.bpmndoc.parser.XMLParser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;

/**
 * TODO
 * @author Daniel Ospina
 */
public class Main {
    
    public static void main(String[] args) {
        String filename = "Diagramita2.bpmn";
        //filename = args[0];
        
        if (args.length < 1 && false) {
            usage();
        }
        else {
            Node node = startParse(filename);
            Pool pool = ModelBuilder.getModel(node);
            draw(pool);
        }
    }
    
    public static Node startParse(String filename) {
        Node node = null;
        try {
            node = XMLParser.parse(new FileInputStream(filename));
        } catch (FileNotFoundException | BPMNParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error parsing " + filename, ex);
        }
        return node;
    }
    
    private static void usage() {
        System.out.println("Usage: xmlechoparser file1");
    }

    private static void draw(Pool pool) {
        System.out.println(pool.getName());
        System.out.println(pool.getDescription());
        System.out.println(pool.getId());
        for (Lane lane : pool.getLanes().values()) {
            System.out.println("---- " + lane.getName() + " " + lane.getId() + " " + lane.getDescription());
            for (Element elem : lane.getElements().values()) {
                System.out.println("----____ " + elem.getName() + " " + elem.getId() + " " + elem.getDescription());
            }
//            for (String key : lane.getElements().keySet()) {
//                System.out.println("----____ " + key);
//            }
        }
    }
}
