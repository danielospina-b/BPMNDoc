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
    
    /**
     * Sets the successor of this element.
     * @param targetElement successor element
     * @param id Task doesn't need the id modifier.
     */
    @Override
    public void setNextConnection(Element targetElement, String id) {
        this.next = targetElement;
    }
    
    /**
     * Sets the predecessor of this element only if it hasn't been set before.
     * @param sourceElement predecessor element.
     */
    @Override
    public void setPreviousConnection(Element sourceElement) {
        if (this.previous == null) this.previous = sourceElement;
    }

    /**
     * Returns the successor of this Task
     * @return successor element.
     */
    @Override
    public Element getNextElement() {
        return next;
    }

    /**
     * Task doesn't have a alternative successor so this method returns null.
     * @return null
     */
    @Override
    public Element getAltNextElement() {
        return null;
    }
    
    /**
     * Returns the predecessor of this Task.
     * @return predecessor element
     */
    @Override
    public Element getPreviousElement() {
        return previous;
    }
    
}
