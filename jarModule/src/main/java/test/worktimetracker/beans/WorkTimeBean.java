package test.worktimetracker.beans;

/**
 * Created by vlad on 03.01.2016.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.entities.WorktimeEntity;
import test.worktimetracker.exception.TaskException;
import test.worktimetracker.exception.UserException;

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

    public List<Object> getInfoAboutTasks(UserttEntity user) throws  Exception{

        Query queryUserByFirstName = entityManager.createNamedQuery("WorktimeEntity.getInfoAboutTasks");
        LOG.debug(user.getUsrName());
        queryUserByFirstName.setParameter("idUser", user);
        List<Object> result_t = queryUserByFirstName.getResultList();
        LOG.debug( "getInfoAboutTasks"  + result_t.size());
        LOG.info("!!!!!!!!!");
        return result_t;

    }

    /**
     * <p>This method gets current the task of user</p>
     * @return TaskEntity (task)
     */
    public  TaskEntity getCurrentTaskOfUser(Integer id)throws  Exception{


       UserttEntity user = sessionUSR.getCurrentUser(id);
        List<WorktimeEntity> listWT = user.getWorktimeCollection();
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

    public  void finishTaskOfUser(String taskName,Integer userI) throws TaskException,UserException{


            UserttEntity user = sessionUSR.getCurrentUser(userI);
            List<WorktimeEntity> listWT = user.getWorktimeCollection();
            for (WorktimeEntity wt : listWT) {

                if ((wt.getWtEnd() == null) && (wt.getTskId().getTskIname().compareToIgnoreCase(taskName) == 0)) {
                    wt.setWtEnd(BigInteger.valueOf(new Date().getTime()));
                    entityManager.merge(wt);
                    LOG.debug(taskName);

                }
            }
            


    }

    public BigInteger getMaxBeginDateTask( String taskName, Integer  userI)throws  Exception{
        UserttEntity user = sessionUSR.getCurrentUser(userI);
        List<WorktimeEntity> listWT = user.getWorktimeCollection();
        for (WorktimeEntity wt : listWT) {
            if (wt.getWtEnd() == null) {
                LOG.debug("current task "+wt.getTskId().getTskIname());
                return wt.getWtBegin();
            }
        }

        return  new BigInteger("0");

    }
}
