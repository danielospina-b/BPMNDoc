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

    public String getName() {
        return "[" + type + " Task] " + name;
    }
    
}
