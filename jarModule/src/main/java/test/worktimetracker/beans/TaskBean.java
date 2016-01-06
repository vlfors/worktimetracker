package test.worktimetracker.beans;

import org.hibernate.Hibernate;
import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.entities.WorktimeEntity;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.math.BigInteger;

/**
 * Created by vlad on 31.12.2015.
 */

/**
 *
 */
@Stateless
public class TaskBean implements TaskLocal {
    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    SessionOfUserLocal sessionUSR;

    public List<TaskEntity>  getTasks() {
        Query queryTaskAll = entityManager.createNamedQuery("TaskEntity.findAll");

        List<TaskEntity> Tasks = queryTaskAll.getResultList();
        return  Tasks;
    };

    /**
     *<p></p>
     * @return List WorktimeEntitys of User
     */
    public List<WorktimeEntity> getTasksByUser() {
        //Query queryUserByFirstName = entityManager.createNamedQuery("TaskEntity.findAll");

     //   List<WorktimeEntity> Tasks = queryUserByFirstName.getResultList();
        Hibernate.initialize(sessionUSR.getCurrentUser().getWorktimeCollection());
        return  sessionUSR.getCurrentUser().getWorktimeCollection();
    };

    /**
     *<p>Create new task </p>
     * @param task
     */
    public void newTask(TaskEntity task){
     if (task==null) return;
        Query queryUserByFirstName = entityManager.createNamedQuery("TaskEntity.findByTskIname");
        queryUserByFirstName.setParameter("tskIname", task.getTskIname());

        List<TaskEntity> tasks = queryUserByFirstName.getResultList();
        if(tasks.isEmpty()){

       // task.setTskIname( sessionUSR.getCurrentUser().getUsrName());
            entityManager.persist(task);

        }

        tasks = queryUserByFirstName.getResultList();
        TaskEntity taskI = tasks.get(0);
        UserttEntity userI = sessionUSR.getCurrentUser();
        WorktimeEntity wt = new WorktimeEntity();
        wt.setTskId(taskI);
        wt.setUsrId(userI);
        wt.setWtBegin(BigInteger.valueOf( new Date().getTime()));
        entityManager.persist(wt);
        entityManager.refresh(wt);
        entityManager.flush();



        //return ;
    }

    /**
     *
     */
    public void FinishTask(){
        List<WorktimeEntity> list = sessionUSR.getCurrentUser().getWorktimeCollection();

    }
}
