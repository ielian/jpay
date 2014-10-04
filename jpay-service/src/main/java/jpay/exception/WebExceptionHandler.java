package jpay.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * WebExceptionHandler
 */
public class WebExceptionHandler implements ExceptionMapper<DomainException>
{
    public Response toResponse(DomainException exception) {
        Response.Status status;

        status = Response.Status.INTERNAL_SERVER_ERROR;

        return Response.status(status).header("exception", exception.getMessage()).build();
    }

}
