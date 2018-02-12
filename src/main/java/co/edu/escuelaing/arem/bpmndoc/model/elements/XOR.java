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

    @Override
    public void setNextConnection(Element targetElement, String id) {
        if (defaultElemId.equals(id)) {
            this.defaultElem = targetElement;
        }
        else {
            this.alternative = targetElement;
        }
    }

    @Override
    public void setPreviousConnection(Element sourceElement) {
        this.previous = sourceElement;
    }

    @Override
    public Element getNextElement() {
        return defaultElem;
    }

    @Override
    public Element getAltNextElement() {
        return alternative;
    }

    @Override
    public Element getPreviousElement() {
        return previous;
    }
    
    @Override
    public String getName() {
        return "[XOR Gateway] " + super.getName();
    }
    
}
