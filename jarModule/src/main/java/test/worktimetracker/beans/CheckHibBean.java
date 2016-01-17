package test.worktimetracker.beans;
/**
 * Created by vlad on 26.12.2015.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import test.worktimetracker.entities.UserttEntity;

import javax.ejb.Stateless;

/**
 *
 */
@Stateless
public class CheckHibBean implements  CheckHibLocal {
    /**
     *
     */
    public void Check(){
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory =  configuration.buildSessionFactory(builder.build());
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserttEntity user = new UserttEntity();
        user.setUsrName("firstuser");
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
