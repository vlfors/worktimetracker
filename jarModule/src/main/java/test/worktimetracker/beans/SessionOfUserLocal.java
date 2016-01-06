package test.worktimetracker.beans;

/**
 * Created by vlad on 28.12.2015.
 */

import test.worktimetracker.entities.UserttEntity;

import javax.ejb.*;

@Local
public interface SessionOfUserLocal {
    public UserttEntity getSession(String name);
    public UserttEntity getCurrentUser();
    public Boolean checkStatus();
    public Boolean closeSession();

}
