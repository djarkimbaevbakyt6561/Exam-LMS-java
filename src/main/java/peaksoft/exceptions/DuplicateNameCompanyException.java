package peaksoft.exceptions;

public class DuplicateNameCompanyException extends RuntimeException{
    public DuplicateNameCompanyException() {
        super();
    }

    public DuplicateNameCompanyException(String message) {
        super(message);
    }
}
