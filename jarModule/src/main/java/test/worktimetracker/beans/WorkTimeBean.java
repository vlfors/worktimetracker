package test.worktimetracker.beans;



import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.WorktimeEntity;
import test.worktimetracker.exception.TaskException;
import test.worktimetracker.exception.UserException;

/**
 * Created by vlad on 03.01.2016.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class WorkTimeBean implements WorkTimeLocal {
    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    SessionOfUserLocal sessionUSR;

    private static final Log LOG = LogFactory.getLog(WorkTimeBean.class);
    /**
     * <P> This method gets information about tasks of the user</P>
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Object> getInfoAboutTasks() throws  Exception{

        Query queryUserByFirstName = entityManager.createNamedQuery("WorktimeEntity.getInfoAboutTasks");
        LOG.debug(sessionUSR.getCurrentUser().getUsrName());
        queryUserByFirstName.setParameter("idUser", sessionUSR.getCurrentUser());
        List<Object> result_t = queryUserByFirstName.getResultList();
        LOG.debug( "getInfoAboutTasks"  + result_t.size());
        LOG.info("!!!!!!!!!");
        return result_t;

    }

    /**
     * <p>This method gets current the task of user</p>
     * @return TaskEntity (task)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public  TaskEntity getCurrentTaskOfUser()throws  Exception{

            List<WorktimeEntity> listWT = sessionUSR.getCurrentUser().getWorktimeCollection();
            for (WorktimeEntity wt : listWT) {
                if (wt.getWtEnd() == null) {
                    LOG.debug("current task "+wt.getTskId().getTskIname());
                    return wt.getTskId();
                }
            }
            

        return  null;
    }

    /**
     * <p>Completion of the task</p>
     * @param taskName
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public  void finishTaskOfUser(String taskName) throws TaskException,UserException{

            LOG.debug(taskName);
            List<WorktimeEntity> listWT = sessionUSR.getCurrentUser().getWorktimeCollection();
            for (WorktimeEntity wt : listWT) {

                if ((wt.getWtEnd() == null) && (wt.getTskId().getTskIname().compareToIgnoreCase(taskName) == 0)) {
                    wt.setWtEnd(BigInteger.valueOf(new Date().getTime()));
                    entityManager.merge(wt);

                }
            }
            


    }
}
