package co.edu.escuelaing.arem.bpmndoc.model.elements;

import co.edu.escuelaing.arem.bpmndoc.model.Element;

/**
 * This class represents a Start Event in BPMN notation, extends the Element class.
 * @author Daniel Ospina
 */
public class StartEvent extends Element {
    
    private Element next;

    @Override
    public void setNextConnection(Element targetElement, String id) {
        this.next = targetElement;
    }

    @Override
    public void setPreviousConnection(Element sourceElement) {
        
    }

    @Override
    public Element getNextElement() {
        return next;
    }

    @Override
    public Element getAltNextElement() {
        return null;
    }

    @Override
    public Element getPreviousElement() {
        return null;
    }
    
    @Override
    public String getName() {
        return "[Start Event] " + super.getName();
    }
    
}
