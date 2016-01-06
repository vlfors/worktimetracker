package test.worktimetracker.beans;

/**
 * Created by vlad on 28.12.2015.
 */



import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.entities.WorktimeEntity;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;


import javax.ejb.*;
@Startup
@Singleton
public class SessionOfUserBean implements SessionOfUserLocal {
    @PersistenceContext
    private EntityManager entityManager;

    private UserttEntity user = null;

    /**
     *
     * @param name
     * @return
     */
    public UserttEntity getSession(String name){
        if (name.isEmpty()) return null;
        Query queryUserByFirstName = entityManager.createNamedQuery("UserttEntity.findByUsrName");
        queryUserByFirstName.setParameter("usrName", name);
        List<UserttEntity> users = queryUserByFirstName.getResultList();
        if(users.isEmpty()){

            user = new UserttEntity();
            user.setUsrName(name);
            entityManager.persist(user);
            entityManager.refresh(user);
            entityManager.flush();


            return user;
        }
        else{

            user = users.get(0);
            return user;

        }

    };

    /**
     *
     * @return
     */
    public  UserttEntity getCurrentUser(){
        Query queryUserByFirstName = entityManager.createNamedQuery("UserttEntity.findByUsrId");
        queryUserByFirstName.setParameter("usrId", user.getUsrId());
        //List<UserttEntity> users = queryUserByFirstName.getResultList();
        user = (UserttEntity)queryUserByFirstName.getSingleResult();
        return user;
    }

    /**
     *
     * @return
     */
    public Boolean checkStatus(){
        List<WorktimeEntity> list = user.getWorktimeCollection();
        if (list.isEmpty()) return  true;
        for (WorktimeEntity wk:list) {
            if (wk.getWtEnd()==null) return  false;

        }
        return  true;
    }

    /**
     *
     * @return
     */
    public  Boolean closeSession(){
        try {
            user = null;
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

}
