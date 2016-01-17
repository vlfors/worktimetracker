package test.worktimetracker.excetion;

/**
 * Created by vlad on 16.01.2016.
 */
import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }
    public UserException(String message, Throwable cause) {
        super(message, cause);

    }

}

