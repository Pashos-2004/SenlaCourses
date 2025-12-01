package autoService.exception;

public class CsvOperationException extends RuntimeException {
    public CsvOperationException(String message) {
        super(message);
    }
    
    public CsvOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
