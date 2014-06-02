package myscores.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationExceptionMapper.class);
    private static final String KEY_VALUE_REGEX = "\\s*:\\s*";
    private static final String MESSAGE_KEY = "message";

    @Override
    public Response toResponse(ValidationException exception) {
        LOGGER.error("Validation failed", exception);
        Map<String, String> parts = interpretExceptionMessage(exception.getMessage());
        return Response.status(Response.Status.PRECONDITION_FAILED).entity(parts.get(MESSAGE_KEY)).build();
    }

    private Map<String, String> interpretExceptionMessage(String message) {
        Map<String, String> parts = new HashMap<>();
        if (message != null && !message.isEmpty()) {
            Scanner scanner = new Scanner(message);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] keyValue = line.trim().split(KEY_VALUE_REGEX);
                if (keyValue.length == 2) {
                    parts.put(keyValue[0], keyValue[1]);
                }
            }
            scanner.close();
        }
        return parts;
    }
}
