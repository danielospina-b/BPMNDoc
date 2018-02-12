package co.edu.escuelaing.arem.bpmndoc;

import co.edu.escuelaing.arem.bpmndoc.model.Element;
import co.edu.escuelaing.arem.bpmndoc.model.Lane;
import co.edu.escuelaing.arem.bpmndoc.model.Pool;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.io.FileUtils;

/**
 * This class generates the HTML files that represents the BMPN Documentation made from a .bpmn file
 * @author Daniel Ospina
 */
public class HTMLBuilder {
    
    public static final String GENERATION_PATH = "BPMNDoc/";
    private static final String ELEMENT_TEMPLATE = "/templates/element_template.html";
    private static final String INDEX_TEMPLATE = "/templates/index_template.html";
    private static final String LANE_TEMPLATE = "/templates/lane_template.html";
    private static final String ELEMENTS_PATH = GENERATION_PATH + "elements/";
    private static final String ELEMENTS_FROM_GENERATION_PATH = "elements/";
    
    /**
     * Generates the HTML Documentation files of a given Pool Object at a folder in the running classpath.
     * @param pool Pool for Documentation generation
     * @throws FileNotFoundException Thrown when template resource files are not found, these are designated at the static variables in this class.
     * @throws IOException Thrown when I/O exception occurs.
     */
    public static void generateHtml(Pool pool) throws FileNotFoundException, IOException {
        for (Lane lane : pool.getLanes().values()) {
            for (Element element : lane.getElements().values()) {
                generateElementsHtml(element);
            }
        }
        generateIndexHtml(pool);
        
    }
    
    /**
     * This method creates the files that represents Elements in BPMN Syntax
     * @param element Element to generate documentation for.
     * @throws FileNotFoundException Thrown when template resource files are not found, these are designated at the static variables in this class.
     * @throws IOException Thrown when I/O exception occurs.
     */
    private static void generateElementsHtml(Element element) throws FileNotFoundException, IOException {
        InputStream templateFile = HTMLBuilder.class.getResourceAsStream(ELEMENT_TEMPLATE);
        String htmlStr = getFileContent(templateFile);
        htmlStr = htmlStr.replace("{elementName}", element.getName());
        htmlStr = htmlStr.replace("{elementDescription}", element.getDescription());
        htmlStr = htmlStr.replace("{elementId}", element.getId());
        if (element.getNextElement() != null) {
            htmlStr = htmlStr.replace("{elementNextId}", element.getNextElement().getId());
            htmlStr = htmlStr.replace("{elementNextName}", element.getNextElement().getName());
        }
        else {
            htmlStr = htmlStr.replace("{elementNextId}", "");
            htmlStr = htmlStr.replace("{elementNextName}", "");
        }
        if (element.getAltNextElement()!= null) {
            htmlStr = htmlStr.replace("{elementAltId}", element.getAltNextElement().getId());
            htmlStr = htmlStr.replace("{elementAltName}", element.getAltNextElement().getName());
        }
        else {
            htmlStr = htmlStr.replace("{elementAltId}", "");
            htmlStr = htmlStr.replace("{elementAltName}", "");
        }
        if (element.getPreviousElement() != null) {
            htmlStr = htmlStr.replace("{elementPreviousId}", element.getPreviousElement().getId());
            htmlStr = htmlStr.replace("{elementPreviousName}", element.getPreviousElement().getName());
        }
        else {
            htmlStr = htmlStr.replace("{elementPreviousId}", "");
            htmlStr = htmlStr.replace("{elementPreviousName}", "");
        }
        String path = ELEMENTS_PATH + element.getId() + ".html";
        File htmlFile = new File(path);
        FileUtils.writeStringToFile(htmlFile, htmlStr);
    }
    
    /**
     * This method creates the index file for general navigation of a BPMN pool
     * @param pool Pool to generate BPMN Documentation.
     * @throws IOException Thrown when I/O exception occurs.
     */
    private static void generateIndexHtml(Pool pool) throws IOException {
        InputStream templateFile = HTMLBuilder.class.getResourceAsStream(INDEX_TEMPLATE);
        String htmlStr = getFileContent(templateFile);
        htmlStr = htmlStr.replace("{poolName}", pool.getName());
        htmlStr = htmlStr.replace("{poolId}", pool.getId());
        htmlStr = htmlStr.replace("{poolDescription}", pool.getDescription());
        for (Lane lane : pool.getLanes().values()) {
            htmlStr = htmlStr.replace("{lanes}", getLaneHtml(lane) + "{lanes}");
        }
        htmlStr = htmlStr.replace("{lanes}", "");
        String path = GENERATION_PATH + "index.html";
        File htmlFile = new File(path);
        FileUtils.writeStringToFile(htmlFile, htmlStr);
    }

    private static String getLaneHtml(Lane lane) throws IOException {
        InputStream templateFile = HTMLBuilder.class.getResourceAsStream(LANE_TEMPLATE);
        String htmlStr = getFileContent(templateFile);
        htmlStr = htmlStr.replace("{laneName}", lane.getName());
        htmlStr = htmlStr.replace("{laneDescription}", lane.getDescription());
        for (Element elem : lane.getElements().values()) {
            htmlStr = htmlStr.replace("{element}", "<li><a href=\"" + ELEMENTS_FROM_GENERATION_PATH + elem.getId() + ".html" + "\">" + elem.getName() + "</a></li>" + "{element}");
        }
        htmlStr = htmlStr.replace("{element}", "");
        return htmlStr;
    }
    
    /**
     * Gets the string from a InputStream
     * @param fis InputStream to get the String for.
     * @return a String from the InputStream
     * @throws IOException Thrown when I/O exception occurs.
     */
    private static String getFileContent(InputStream fis) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }
    
}
