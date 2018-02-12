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
     * Sets a connection between this element and its successor
     * @param targetElement succesor Element.
     * @param id acts as a modifier in case model logic requires it
     */
    public abstract void setNextConnection(Element targetElement, String id);
    
    /**
     * Sets a connection between this element and its predecessor.
     * @param sourceElement predecessor element.
     */
    public abstract void setPreviousConnection(Element sourceElement);
    
    /**
     * Gets the successor connection element of this element (if it has one).
     * @return Successor element.
     */
    public abstract Element getNextElement();
    
    /**
     * In case element has two successors returns the alternative successor.
     * @return Alternative successor element.
     */
    public abstract Element getAltNextElement();
    
    /**
     * Gets the successor connection element of this element (if it has one).
     * @return Predecessor element.
     */
    public abstract Element getPreviousElement();
    
}
