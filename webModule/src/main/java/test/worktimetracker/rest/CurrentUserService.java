package test.worktimetracker.rest;

import test.worktimetracker.beans.SessionOfUserLocal;
import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.servlets.CheckSession;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

/**
 * Created by vlad on 30.12.2015.
 */

@Path("/user")

public class CurrentUserService implements Service {


    @Context HttpServletRequest servletRequest;
    @EJB
    private SessionOfUserLocal sessionus;

        @GET
        @Produces("application/json")
        public UserttEntity getProductInJSON() {
            try {

                UserttEntity user = new CheckSession().checkSession(servletRequest);
                if (user == null) {
                    return null;

                } else return user;
            }catch (Exception e){
                throw new WebApplicationException("");
            }
        }




}
