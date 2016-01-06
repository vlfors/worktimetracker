package test.worktimetracker.rest;

/**
 * Created by vlad on 24.12.2015.
 */
import test.worktimetracker.beans.SessionOfUserLocal;
import test.worktimetracker.entities.UserttEntity;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageRestService implements Service {

    @EJB
    private SessionOfUserLocal sessionus;

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg) {

        UserttEntity user = sessionus.getCurrentUser();

        String res = "";
        res =user==null?"empty":user.getUsrName();
        String result = "Restful example : " + msg+res;

        return Response.status(200).entity(result).build();

    }

}