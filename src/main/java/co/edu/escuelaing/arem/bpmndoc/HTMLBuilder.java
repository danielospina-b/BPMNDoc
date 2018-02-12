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
import java.net.URISyntaxException;
import org.apache.commons.io.FileUtils;

/**
 * //TODO
 * @author Daniel Ospina
 */
public class HTMLBuilder {
    
    public static final String GENERATION_PATH = "BPMNDoc/";
    private static final String ELEMENT_TEMPLATE = "/templates/element_template.html";
    private static final String INDEX_TEMPLATE = "/templates/index_template.html";
    private static final String LANE_TEMPLATE = "/templates/lane_template.html";
    private static final String ELEMENTS_PATH = GENERATION_PATH + "elements/";
    private static final String ELEMENTS_FROM_GENERATION_PATH = "elements/";

    public static void generateHtml(Pool pool) throws FileNotFoundException, IOException, URISyntaxException {
        for (Lane lane : pool.getLanes().values()) {
            for (Element element : lane.getElements().values()) {
                generateElementsHtml(element);
            }
        }
        generateIndexHtml(pool);
        
    }

    private static void generateElementsHtml(Element element) throws FileNotFoundException, IOException, URISyntaxException {
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

    private static void generateIndexHtml(Pool pool) throws IOException, URISyntaxException {
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

    private static String getLaneHtml(Lane lane) throws IOException, URISyntaxException {
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
