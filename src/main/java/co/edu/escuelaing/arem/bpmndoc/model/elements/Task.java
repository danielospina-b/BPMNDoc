package co.edu.escuelaing.arem.bpmndoc.model.elements;

import co.edu.escuelaing.arem.bpmndoc.model.Element;

/**
 * This class represents a Task in BPMN notation, extends the Element class.
 * @author Daniel Ospina
 */
public class Task extends Element {
    
    private Element next;
    private Element previous;
    private String type;

    public Task(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return "[" + type + " Task] " + name;
    }

    @Override
    public void setNextConnection(Element targetElement, String id) {
        this.next = targetElement;
    }

    @Override
    public void setPreviousConnection(Element sourceElement) {
        this.previous = sourceElement;
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
        return previous;
    }
    
}
