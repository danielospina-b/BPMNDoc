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

    public Pool() {
        lanes = new HashMap<>();
        name = "";
        description = "";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public HashMap<String, Lane> getLanes() {
        return lanes;
    }
    
    public void addLane(String id, Lane value) {
        lanes.put(id, value);
    }
    
}
