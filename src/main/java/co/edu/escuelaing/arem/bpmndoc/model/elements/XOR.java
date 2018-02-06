package co.edu.escuelaing.arem.bpmndoc.model.elements;

import co.edu.escuelaing.arem.bpmndoc.model.Element;

/**
 * This class represents a XOR Gateway in BPMN notation, extends the Element class.
 * @author Daniel Ospina
 */
public class XOR extends Element {
    
    private String defaultElemId;
    private String alternative;

    @Override
    public String getDescription() {
        return "[Default: " + defaultElemId + "] " + description;
    }

    public void setDefaultElementId(String defaultElem) {
        this.defaultElemId = defaultElem;
    }
    
}
