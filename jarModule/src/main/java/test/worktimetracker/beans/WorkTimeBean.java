package test.worktimetracker.beans;



import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.WorktimeEntity;

/**
 * Created by vlad on 03.01.2016.
 */
@Stateless
public class WorkTimeBean implements WorkTimeLocal {
    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    SessionOfUserLocal sessionUSR;

    /**
     * <P>Getting information about tasks of the user</P>
     * @return
     */
    public List<Object> getInfoAboutTasks(){
        Query queryUserByFirstName = entityManager.createNamedQuery("WorktimeEntity.getInfoAboutTasks");
        queryUserByFirstName.setParameter("idUser", sessionUSR.getCurrentUser());

        List<Object> result_t = queryUserByFirstName.getResultList();
        return result_t;
    }

    /**
     * <p>Getting current the task of user</p>
     * @return TaskEntity (task)
     */
    public  TaskEntity getCurrentTaskOfUser(){

        List<WorktimeEntity> listWT = sessionUSR.getCurrentUser().getWorktimeCollection();
        for (WorktimeEntity wt :listWT){
            if (wt.getWtEnd() == null) return wt.getTskId();
        };

        return  null;
    }

    /**
     * <p>Completion of the task</p>
     * @param taskName
     */
    public  void finishTaskOfUser(String taskName){

        List<WorktimeEntity> listWT = sessionUSR.getCurrentUser().getWorktimeCollection();
        for (WorktimeEntity wt :listWT){

            if ((wt.getWtEnd() == null) && (wt.getTskId().getTskIname().compareToIgnoreCase(taskName)==0)) {
                wt.setWtEnd(BigInteger.valueOf( new Date().getTime()));
                entityManager.merge(wt);

            }
        };


    }
}
