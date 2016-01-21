package test.worktimetracker.beans;

/**
 * Created by vlad on 28.12.2015.
 */

import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.exception.UserException;

import javax.ejb.*;

@Local
public interface SessionOfUserLocal {
    UserttEntity getSession(String name) throws UserException;
    UserttEntity getCurrentUser() throws UserException;
    Boolean checkStatus() throws UserException;
    Boolean closeSession() throws UserException;

}
