package test.worktimetracker.rest;

/**
 * Created by vlad on 24.12.2015.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.worktimetracker.beans.SessionOfUserLocal;
import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.exception.WorkTimeException;
import test.worktimetracker.servlets.CheckSession;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageRestService implements Service {

    @Context
    HttpServletRequest servletRequest;

    @EJB
    private SessionOfUserLocal sessionus;

    private static final Log LOG = LogFactory.getLog(TasksService.class);

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg) throws WorkTimeException {
        try {
            UserttEntity user = new CheckSession().checkSession(servletRequest);

            String res;
            res = user == null ? "empty" : user.getUsrName();
            String result = "Restful example : " + msg + res;

            return Response.status(200).entity(result).build();
        }catch (Exception e){
            LOG.error(e);
            throw new WorkTimeException("Task not completed", e);
        }

    }

}