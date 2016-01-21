package test.worktimetracker.exception;

/**
 * Created by vlad on 15.01.2016.
 */
import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
    public TaskException(String message, Throwable cause) {
        super(message, cause);

    }

}
