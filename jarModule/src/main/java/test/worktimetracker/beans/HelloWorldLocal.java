package test.worktimetracker.beans;

/**
 * Created by vlad on 26.12.2015.
 */
import javax.ejb.*;
@Local
public interface HelloWorldLocal {
    /**
     *
     * @param name
     * @return
     */
    public String getMessage(String name);

}
