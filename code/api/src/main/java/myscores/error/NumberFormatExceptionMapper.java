package myscores.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NumberFormatExceptionMapper implements ExceptionMapper<NumberFormatException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberFormatExceptionMapper.class);

    @Override
    public Response toResponse(NumberFormatException exception) {
        String message = "Request id must be numeric";
        LOGGER.error(message, exception);
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }
}
