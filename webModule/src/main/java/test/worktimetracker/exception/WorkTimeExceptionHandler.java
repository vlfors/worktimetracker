package test.worktimetracker.exception;

/**
 * Created by vlad on 16.01.2016.
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class WorkTimeExceptionHandler implements ExceptionMapper<WorkTimeException>  {

    public Response toResponse(WorkTimeException exception)
    {
        return Response.status(Status.BAD_REQUEST).entity("[{\"message\":\""+ exception.getMessage()+"\"}]").build();
    }

}
