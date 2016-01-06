package test.worktimetracker.rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


import org.hibernate.Hibernate;
import test.worktimetracker.beans.SessionOfUserLocal;
import test.worktimetracker.beans.TaskLocal;
import test.worktimetracker.beans.WorkTimeLocal;
import test.worktimetracker.entities.TaskEntity;
import test.worktimetracker.entities.WorktimeEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created by vlad on 30.12.2015.
 */

@Path("/task")
public class TasksService implements Service {
    @EJB
    private TaskLocal tbean;
    @EJB
    private   SessionOfUserLocal sessionus;
    @EJB
    private WorkTimeLocal workTimeLocal;

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createTask(TaskEntity task) {

        tbean.newTask(task);
        String result = "Task created : " + task.toString();
        return Response.status(201).entity(result).build();

    }

    /**
     *
     * @return
     */

    @GET
    @Path("/get")
    @Produces("application/json")
    //public Response getTasks() {
    public List<TaskEntity> getTasks() {


       // return tbean.getTasks();
        return tbean.getTasks();
        //return Response.ok( tbean.getTasks()).build();
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/getus")
    @Produces("application/json")
    //public Response getTasks() {
    public Collection<WorktimeEntity> getTasksByUser() {

        //Hibernate.initialize(sessionus.getCurrentUser().getWorktimeCollection());
        return sessionus.getCurrentUser().getWorktimeCollection();
        //return Response.ok( tbean.getTasks()).build();
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/info")
    @Produces("application/json")
    //public Response getTasks() {
    public Collection<Object> getInfo() {

        //Hibernate.initialize(sessionus.getCurrentUser().getWorktimeCollection());
        return workTimeLocal.getInfoAboutTasks();
        //return Response.ok( tbean.getTasks()).build();
    }

    @GET
    @Path("/ctask")
    @Produces("application/json")
    public TaskEntity getCurrentTask(){
        return workTimeLocal.getCurrentTaskOfUser();
    }

    @POST
    @Path("/ftask")
    @Consumes("application/json")
    public Response finishTask(String name) {


        workTimeLocal.finishTaskOfUser(name);
        String result = "Task completed : " + name;
        return Response.status(201).entity(result).build();

    }
}