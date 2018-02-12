package co.edu.escuelaing.arem.bpmndoc.parser;

/**
 * Exception intended to deal with errors at the parsing stage of BPMNDoc
 * @author Daniel Ospina
 */
public class BPMNParseException extends Exception {

    public BPMNParseException() {
    }

    public BPMNParseException(String message) {
        super(message);
    }

    public BPMNParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BPMNParseException(Throwable cause) {
        super(cause);
    }
    
}
