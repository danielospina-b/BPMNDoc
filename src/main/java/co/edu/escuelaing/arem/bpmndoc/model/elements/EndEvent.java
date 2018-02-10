package co.edu.escuelaing.arem.bpmndoc.model.elements;

import co.edu.escuelaing.arem.bpmndoc.model.Element;

/**
 * This class represents a End Event in BPMN notation, extends the Element class.
 * @author Daniel Ospina
 */
public class EndEvent extends Element {
    
    private Element previous;

    @Override
    public void setNextConnection(Element targetElement, String id) {
        
    }

    @Override
    public void setPreviousConnection(Element sourceElement) {
        this.previous = sourceElement;
    }

    @Override
    public Element getNextElement() {
        return null;
    }

    @Override
    public Element getAltNextElement() {
        return null;
    }

    @Override
    public Element getPreviousElement() {
        return previous;
    }
    
}
