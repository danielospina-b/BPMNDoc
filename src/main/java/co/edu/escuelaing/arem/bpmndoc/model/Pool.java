package co.edu.escuelaing.arem.bpmndoc.model;

import java.util.HashMap;

/**
 * This class represents a Pool in BPMN notation.
 * @author Daniel Ospina
 */
public class Pool {
    
    private String name;
    private String description;
    private String id;
    private HashMap<String, Lane> lanes;
    
}
