package test.worktimetracker.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.entities.WorktimeEntity;
import test.worktimetracker.excetion.TaskException;
import test.worktimetracker.excetion.UserException;


import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.math.BigInteger;


/**
 *
 */
@Stateless(name="Task")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TaskBean implements TaskLocal {
    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    SessionOfUserLocal sessionUSR;

    private static final Log LOG = LogFactory.getLog(TaskBean.class);

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<TaskEntity>  getTasks() throws TaskException {

            Query queryTaskAll = entityManager.createNamedQuery("TaskEntity.findAll");
            List<TaskEntity> tasks = (List<TaskEntity>)queryTaskAll.getResultList();
            return tasks;


    }

    /**
     *<p>This method returns list of Tasks. </p>
     * @return  List<WorktimeEntity>
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<WorktimeEntity> getTasksByUser() throws TaskException, UserException {

        Hibernate.initialize(sessionUSR.getCurrentUser().getWorktimeCollection());
        return  sessionUSR.getCurrentUser().getWorktimeCollection();
    }

    /**
     *<p>This method creates new task </p>
     * @param task
     */


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void newTask(TaskEntity task) throws TaskException, UserException {
        if (task == null) {
            throw new TaskException("Task is empty!");
        }
        if (!sessionUSR.checkStatus()) {
            throw new TaskException("The user got task. He can't get more!");
        }
        
            LOG.debug(task.getTskIname());
        
            Query queryUserByFirstName = entityManager.createNamedQuery("TaskEntity.findByTskIname");
            queryUserByFirstName.setParameter("tskIname", task.getTskIname());

            List<TaskEntity> tasks = (List<TaskEntity>)queryUserByFirstName.getResultList();
            if(tasks.isEmpty()){

                // task.setTskIname( sessionUSR.getCurrentUser().getUsrName());
                entityManager.persist(task);

            }

            tasks = (List<TaskEntity>)queryUserByFirstName.getResultList();
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

}

/**
 * Created by vlad on 31.12.2015.
 */
