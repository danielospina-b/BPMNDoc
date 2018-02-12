package co.edu.escuelaing.arem.bpmndoc.model;

/**
 * This abstract class represents an Element (like Task, XOR gateway, Events) in BPMN notation.
 * @author Daniel Ospina
 */
public abstract class Element {
    
    protected String name;
    protected String id;
    protected String description;
    
    public Element() {
        description = "";
        name = "";
    }
    
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
    
    /**
     * 
     * @param targetElement
     * @param id acts as a modifier in case model logic requires it
     */
    public abstract void setNextConnection(Element targetElement, String id);

    public abstract void setPreviousConnection(Element sourceElement);
    
    public abstract Element getNextElement();
    
    public abstract Element getAltNextElement();
    
    public abstract Element getPreviousElement();
    
}
