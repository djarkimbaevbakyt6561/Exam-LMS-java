package peaksoft.exceptions;

import java.util.NoSuchElementException;

public class LoginDetailsNotFoundException extends NoSuchElementException {

    public LoginDetailsNotFoundException() {
        super();
    }

    public LoginDetailsNotFoundException(String message) {
        super(message);
    }
}
