package co.edu.escuelaing.arem.bpmndoc.model;

/**
 * This abstract class represents an Element (like Task, XOR gateway, Events) in BPMN notation.
 * @author Daniel Ospina
 */
public abstract class Element {
    
    protected String name;
    protected String id;
    protected String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
