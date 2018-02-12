package co.edu.escuelaing.arem.bpmndoc.model.elements;

import co.edu.escuelaing.arem.bpmndoc.model.Element;

/**
 * This class represents a XOR Gateway in BPMN notation, extends the Element class.
 * @author Daniel Ospina
 */
public class XOR extends Element {
    
    private Element previous;
    private String defaultElemId;
    private Element defaultElem;
    private Element alternative;

    @Override
    public String getDescription() {
        return "[Default option: " + defaultElem.getName() + "] " + description;
    }

    public void setDefaultElementId(String defaultElem) {
        this.defaultElemId = defaultElem;
    }

    /**
     * Sets either the next or alternative connection of this XOR Gateway.
     * @param targetElement successor element.
     * @param id id modifier to decide if its the alternative or default connection.
     */
    @Override
    public void setNextConnection(Element targetElement, String id) {
        if (defaultElemId.equals(id)) {
            this.defaultElem = targetElement;
        }
        else {
            this.alternative = targetElement;
        }
    }

    /**
     * Sets the predecessor of this XOR Gateway.
     * @param sourceElement predecessor element.
     */
    @Override
    public void setPreviousConnection(Element sourceElement) {
        this.previous = sourceElement;
    }
    
    /**
     * Gets the successor for this XOR Gateway
     * @return successor element
     */
    @Override
    public Element getNextElement() {
        return defaultElem;
    }
    
    /**
     * Gets the alternative successor for this XOR Gateway
     * @return alternative successor element
     */
    @Override
    public Element getAltNextElement() {
        return alternative;
    }

    /**
     * Gets the predecessor of this XOR Gateway
     * @return predecessor element.
     */
    @Override
    public Element getPreviousElement() {
        return previous;
    }
    
    @Override
    public String getName() {
        return "[XOR Gateway] " + super.getName();
    }
    
}
