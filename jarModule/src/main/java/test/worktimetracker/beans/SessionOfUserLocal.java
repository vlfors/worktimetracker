package test.worktimetracker.beans;

/**
 * Created by vlad on 28.12.2015.
 */

import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.exception.UserException;

import javax.ejb.Local;

@Local
public interface SessionOfUserLocal {
    UserttEntity getSession(String name) throws UserException;
    UserttEntity getCurrentUser( Integer id) throws UserException;
    Boolean checkStatus(Integer id) throws UserException;
    Boolean closeSession() throws UserException;

}
