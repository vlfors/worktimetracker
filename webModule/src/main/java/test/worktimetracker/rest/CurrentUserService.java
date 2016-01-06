package test.worktimetracker.rest;

import test.worktimetracker.beans.SessionOfUserLocal;
import test.worktimetracker.entities.UserttEntity;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by vlad on 30.12.2015.
 */

@Path("/user")

public class CurrentUserService implements Service {


    @EJB
    private SessionOfUserLocal sessionus;

        @GET
        @Produces("application/json")
        public UserttEntity getProductInJSON() {

            UserttEntity user = sessionus.getCurrentUser();
            if (user == null) {
                return null;

            } else return user;
        }




}
