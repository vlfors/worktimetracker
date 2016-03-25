package test.worktimetracker.beans;

import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.entities.WorktimeEntity;
import test.worktimetracker.exception.TaskException;
import test.worktimetracker.exception.UserException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlad on 31.12.2015.
 */
@Local
public interface TaskLocal {
    List<TaskEntity>  getTasks() throws TaskException;
    void newTask(TaskEntity task,Integer id) throws TaskException, UserException;
    //TEST
    List<WorktimeEntity> getTasksByUser(UserttEntity user)  throws TaskException, UserException;
}
