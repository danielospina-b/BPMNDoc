package co.edu.escuelaing.arem.bpmndoc.model.elements;

import co.edu.escuelaing.arem.bpmndoc.model.Element;

/**
 * This class represents a Start Event in BPMN notation, extends the Element class.
 * @author Daniel Ospina
 */
public class StartEvent extends Element {
    
    private Element next;
    
    /**
     * Sets the element connected after this StartEvent
     * @param targetElement Element after
     * @param id modifier not used
     */
    @Override
    public void setNextConnection(Element targetElement, String id) {
        this.next = targetElement;
    }
    
    /**
     * Start Event doesn't have a predecessor so this method does nothing.
     * @param sourceElement predecessor not used
     */
    @Override
    public void setPreviousConnection(Element sourceElement) {
        
    }

    @Override
    public Element getNextElement() {
        return next;
    }
    
    /**
     * Start Event doesn't have a alternative successor so this method returns null.
     * @return null
     */
    @Override
    public Element getAltNextElement() {
        return null;
    }
    
    /**
     * Start Event doesn't have a predecessor so this method returns null.
     * @return null
     */
    @Override
    public Element getPreviousElement() {
        return null;
    }
    
    @Override
    public String getName() {
        return "[Start Event] " + super.getName();
    }
    
}
