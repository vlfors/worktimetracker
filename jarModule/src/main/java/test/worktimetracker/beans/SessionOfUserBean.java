package test.worktimetracker.beans;

/**
 * Created by vlad on 28.12.2015.
 */





import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.entities.WorktimeEntity;
import test.worktimetracker.exception.UserException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import javax.ejb.*;


@Startup
@Singleton
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SessionOfUserBean implements SessionOfUserLocal {
    @PersistenceContext
    private EntityManager entityManager;

    private volatile UserttEntity user = null;

    private static final Log LOG = LogFactory.getLog(SessionOfUserBean.class);
    /**
     * <p>This method creates session of user</p>
     * @param name  user's name
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserttEntity getSession ( String name ) throws UserException {


        if ( name.isEmpty ( ) )
            throw new UserException ( "Name of new user is empty" );
        try {

            name = name.toLowerCase ( ).trim ( );
            Query queryUserByFirstName = entityManager.createNamedQuery ( "UserttEntity.findByUsrName" );
            queryUserByFirstName.setParameter ( "usrName", name );
            List<UserttEntity> users = queryUserByFirstName.getResultList ( );
            if ( users.isEmpty ( ) ) {

                user = new UserttEntity ( );
                user.setUsrName ( name );
                entityManager.persist ( user );
                entityManager.refresh ( user );
                entityManager.flush ( );

                return user;
            } else {

                user = users.get ( 0 );
                return user;

            }

        } catch ( Exception e ) {

            throw new UserException ( "An error ocurred create session of user", e );
        }
    }



    /**
     * <p>This method return user of session</p>
     *
     * @return UserttEntity
     */

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserttEntity getCurrentUser ( ) throws UserException {
        try {

            Query queryUserByFirstName = entityManager.createNamedQuery ( "UserttEntity.findByUsrId" );
            queryUserByFirstName.setParameter ( "usrId", user.getUsrId ( ) );
            //List<UserttEntity> users = queryUserByFirstName.getResultList();
            user = ( UserttEntity ) queryUserByFirstName.getSingleResult ( );

            return user;
        } catch ( Exception e ) {

            throw new UserException ( "", e );
        }
    }

    /**
     *<p>This method checks status of the user </p>
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean checkStatus() throws UserException {
        try {

            List<WorktimeEntity> list = user.getWorktimeCollection();
            if (list.isEmpty()) return true;
            for (WorktimeEntity wk : list) {
                if (wk.getWtEnd() == null) return false;

            }

            return true;
        }catch (Exception e){

            throw new UserException("An error ocurred check the user's status   ",e);
        }
    }

    /**
     *
     * @return
     */
    @Remove
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean closeSession ( ) throws UserException {
        try {
            user = null;
        } catch ( Exception e ) {

            throw new UserException ( "Close session is failed", e );

        }
        return true;
    }

}
