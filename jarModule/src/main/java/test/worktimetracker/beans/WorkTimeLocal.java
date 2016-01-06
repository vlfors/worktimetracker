package test.worktimetracker.beans;

import test.worktimetracker.entities.TaskEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vlad on 03.01.2016.
 */
@Local
public interface WorkTimeLocal {
    public List<Object> getInfoAboutTasks();
    public TaskEntity getCurrentTaskOfUser();
    public  void finishTaskOfUser(String taskName);
}
