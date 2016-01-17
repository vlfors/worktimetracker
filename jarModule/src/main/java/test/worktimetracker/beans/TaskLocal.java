package test.worktimetracker.beans;

import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.WorktimeEntity;
import test.worktimetracker.excetion.TaskException;
import test.worktimetracker.excetion.UserException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlad on 31.12.2015.
 */
@Local
public interface TaskLocal {
    List<TaskEntity>  getTasks() throws TaskException;
    void newTask(TaskEntity task) throws TaskException, UserException;
    //TEST
    List<WorktimeEntity> getTasksByUser()  throws TaskException, UserException;
}
