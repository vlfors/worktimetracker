package test.worktimetracker.beans;

/**
 * Created by vlad on 26.12.2015.
 */

import javax.ejb.*;

@Stateless
public class HelloWorldBean implements HelloWorldLocal {
    /**
     * @param name
     * @return
     */
    public String getMessage ( String name ) {
        return "Hello" + name + "!";
    }
}
