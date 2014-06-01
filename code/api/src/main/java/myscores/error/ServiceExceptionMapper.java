package myscores.error;

import myscores.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionMapper.class);

    @Override
    public Response toResponse(ServiceException exception) {
        LOGGER.error("Service call failed", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }
}
