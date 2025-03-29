package gr.cf.java.app.exceptions;

public class UserIdAlreadyExistsException extends RuntimeException {

    public UserIdAlreadyExistsException(String message) {
        super(message);
    }
}
