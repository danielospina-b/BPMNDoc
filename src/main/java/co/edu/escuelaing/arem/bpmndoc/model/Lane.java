package co.edu.escuelaing.arem.bpmndoc.model;

import java.util.HashMap;

/**
 * This class represents a Lane in BPMN notation.
 * @author Daniel Ospina
 */
public class Lane {
    
    private String name;
    private String description;
    private String id;
    private HashMap<String, Element> elements;
    
}
