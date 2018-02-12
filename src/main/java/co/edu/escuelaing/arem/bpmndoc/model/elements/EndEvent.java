package co.edu.escuelaing.arem.bpmndoc.model.elements;

import co.edu.escuelaing.arem.bpmndoc.model.Element;

/**
 * This class represents a End Event in BPMN notation, extends the Element class.
 * @author Daniel Ospina
 */
public class EndEvent extends Element {
    
    private Element previous;
    
    /**
     * End Event doesn't have a successor so this method does nothing.
     * @param targetElement successor element not used
     * @param id modifier not used
     */
    @Override
    public void setNextConnection(Element targetElement, String id) {
        
    }
    
    /**
     * Sets the element connected before this End Event.
     * @param sourceElement predecessor element 
     */
    @Override
    public void setPreviousConnection(Element sourceElement) {
        this.previous = sourceElement;
    }
    
    /**
     * End Event doesn't have a successor so this method return null.
     * @return null
     */
    @Override
    public Element getNextElement() {
        return null;
    }
    
    /**
     * End Event doesn't have a alternative successor so this method returns null.
     * @return null
     */
    @Override
    public Element getAltNextElement() {
        return null;
    }
    
    /**
     * Returns the element connected before this End Event.
     * @return Previous element.
     */
    @Override
    public Element getPreviousElement() {
        return previous;
    }
    
    @Override
    public String getName() {
        return "[End Event] " + super.getName();
    }
    
}
