package test.worktimetracker.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import test.worktimetracker.beans.SessionOfUserLocal;
import test.worktimetracker.beans.TaskLocal;
import test.worktimetracker.beans.WorkTimeLocal;
import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.UserttEntity;
import test.worktimetracker.entities.WorktimeEntity;
import test.worktimetracker.exception.WorkTimeException;
import test.worktimetracker.servlets.CheckSession;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

/**
 * Created by vlad on 30.12.2015.
 */

@Path("/task")
public class TasksService implements Service {
    @Context
    private HttpServletRequest servletRequest;
    @EJB
    private TaskLocal tbean;
    @EJB
    private   SessionOfUserLocal sessionus;
    @EJB
    private WorkTimeLocal workTimeLocal;

    private static final Log LOG = LogFactory.getLog(TasksService.class);


    /**
     *<p>Creating new task</p>
     * @param task
     * @return
     */
    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createTask(TaskEntity task)throws WorkTimeException {

        try {
            tbean.newTask(task,new  CheckSession().checkSession(servletRequest).getUsrId());
            String result = "[{\"message\":\"Task created : " + task.toString()+"\"}]";
            return Response.status(201).entity(result).build();
        }catch (Exception e){
            LOG.error(e);
            throw  new WorkTimeException(e.getMessage(),e);
        }
        //return Response.status(400).entity("Error").build();

    }

    /**
     * <p></p>
     *
     * @return List of tasks in format  json
     */

    @GET
    @Path("/get")
    @Produces("application/json")
    //public Response getTasks() {
    public List<TaskEntity> getTasks() {
        try {

            // return tbean.getTasks();
            return tbean.getTasks();
            //return Response.ok( tbean.getTasks()).build();
        }catch (Exception e){
            LOG.error(e);
        }
        return null;
    }

    /**
     * <p>Getting all types tasks</p>
     * @return json of list  WorktimeEntity
     */
    @GET
    @Path("/getus")
    @Produces("application/json")
    //public Response getTasks() {
    public Collection<WorktimeEntity> getTasksByUser() throws WorkTimeException {

        //Hibernate.initialize(sessionus.getCurrentUser().getWorktimeCollection());
        try{
            LOG.info("user - "+ "begin!");
            UserttEntity user = new  CheckSession().checkSession(servletRequest);
            LOG.info("user - " +user.getUsrName());
            return  user.getWorktimeCollection();
        }catch (Exception e){
            LOG.error(e);
            throw new WorkTimeException("An error occurred, tasks not found",e);
        }
            //return Response.ok( tbean.getTasks()).build();
    }

    /**
     *<p>Getting information about the tasks  for current user</p>
     * @return
     */
    @GET
    @Path("/info")
    @Produces("application/json")
    //public Response getTasks() {
    public Collection<Object> getInfo() throws WorkTimeException {

        //Hibernate.initialize(sessionus.getCurrentUser().getWorktimeCollection());
        try {
            return workTimeLocal.getInfoAboutTasks(new  CheckSession().checkSession(servletRequest));
        }catch (Exception e){
            throw  new  WorkTimeException("Tasks not found",e);
        }
        //return Response.ok( tbean.getTasks()).build();
      //  return null;
    }

    /**
     *<P>Getting current the task in work</P>
     * @return
     */
    @GET
    @Path("/ctask")
    @Produces("application/json")
    public TaskEntity getCurrentTask() throws WorkTimeException{
        try {

            UserttEntity user = new CheckSession().checkSession(servletRequest);
            TaskEntity task = null;
            if (user != null) {
                LOG.info(user.getUsrName());
                task = workTimeLocal.getCurrentTaskOfUser(user.getUsrId());
                LOG.info(task.getTskIname());

            }
            return task;
        }catch (Exception e){
            LOG.error(e);
            throw new WorkTimeException("Current task not found!",e);
        }

    }
    @GET
    @Path("/cbegin")
    @Produces("application/json")
    public BigInteger getBeginTimeExecuteTask() throws WorkTimeException{
        try {

            UserttEntity user = new CheckSession().checkSession(servletRequest);
            TaskEntity task = null;
            BigInteger timeb = null;
            if (user != null) {
                LOG.info(user.getUsrName());
                task = workTimeLocal.getCurrentTaskOfUser(user.getUsrId());
                timeb = workTimeLocal.getMaxBeginDateTask(task.getTskIname(),user.getUsrId());
                LOG.info(task.getTskIname());

            }
            return timeb;
        }catch (Exception e){
            LOG.error(e);
            throw new WorkTimeException("Current task not found!",e);
        }

    }
    /**
     *<P>This method  completes the task </P>
     * @param  name name of the Task
     * @return Response
     */
    @POST
    @Path("/ftask")
    @Consumes("application/json")
    public Response finishTask(String name)  throws WorkTimeException {

        try {
            String result;
            if(name.isEmpty()){
                throw new WorkTimeException( "Task's name is empty");
              //  result = "Task's name is empty";
               // return Response.status(400).entity(result).build();
            }
            UserttEntity user =  new  CheckSession().checkSession(servletRequest);
            if (user != null) {
                workTimeLocal.finishTaskOfUser(name, user.getUsrId());
                result = "[{\"message\":\"Task completed : " + name+"\"}]";
                LOG.info(result);
                return Response.status(201).entity(result).build();
            }
        }catch (Exception e){
            LOG.error(e);
            throw new WorkTimeException("Task not completed", e);
        }
        ///return Response.status(400).entity("Error").build();
        return Response.status(200).entity("").build();
    }
}
