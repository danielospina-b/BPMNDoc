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

    public Lane() {
        elements = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Element> getElements() {
        return elements;
    }
    
    public void addElement(String key, Element value) {
       elements.put(key, value);
    }
    
}
