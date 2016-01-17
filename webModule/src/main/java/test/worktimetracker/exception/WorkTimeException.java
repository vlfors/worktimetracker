package test.worktimetracker.exception;

/**
 * Created by vlad on 16.01.2016.
 */
import java.io.Serializable;

public class WorkTimeException extends Exception implements Serializable
{
    private static final long serialVersionUID = 1L;
    public WorkTimeException() {
        super();
    }
    public WorkTimeException(String msg)   {
        super(msg);
    }
    public WorkTimeException(String msg, Exception e)  {
        super(msg, e);
    }
}
