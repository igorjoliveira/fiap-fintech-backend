package fiap.fintech.backend.domain.common;

public class EntradaDadoInvalidaException extends Exception {
    // Constructor that accepts a message
    public EntradaDadoInvalidaException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public EntradaDadoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
