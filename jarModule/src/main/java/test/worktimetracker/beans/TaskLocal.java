package test.worktimetracker.beans;

import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.WorktimeEntity;

import javax.ejb.Local;
import java.util.Collection;
import java.util.List;

/**
 * Created by vlad on 31.12.2015.
 */
@Local
public interface TaskLocal {
    public List<TaskEntity>  getTasks();
    public void newTask(TaskEntity task);
    //TEST
    public List<WorktimeEntity> getTasksByUser();
}
