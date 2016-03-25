package test.worktimetracker.beans;

import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.UserttEntity;

import javax.ejb.Local;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by vlad on 03.01.2016.
 */
@Local
public interface WorkTimeLocal {
    List<Object> getInfoAboutTasks(UserttEntity user)throws  Exception;
    TaskEntity getCurrentTaskOfUser(Integer id) throws  Exception;
    void finishTaskOfUser( String taskName, Integer  user)throws  Exception;
    BigInteger getMaxBeginDateTask( String taskName, Integer  userI)throws  Exception;
}
