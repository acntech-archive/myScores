package myscores.resources;

import myscores.Paths;
import myscores.constants.ContentType;
import myscores.domain.Gambler;
import myscores.services.GamblerService;
import myscores.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path(Paths.GAMBLER)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class GamblerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerResource.class);

    @Inject
    private GamblerService service;

    @GET
    public Gambler root() {
        LOGGER.info("Get gambler");
        Gambler gambler = new Gambler();
        gambler.setId(1);
        gambler.setName("Demo Gambler");
        return gambler;
    }

    @GET
    @Path(Paths.GET)
    public Gambler get(@PathParam(Paths.ID) String id) {
        LOGGER.info("Get gambler with id {}", id);
        Gambler gambler = null;
        try {
            gambler = service.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            LOGGER.error("Gambler id must be numeric");
        } catch (ServiceException e) {
            LOGGER.error("Getting gambler with id " + id + " failed", e);
        }
        return gambler;
    }

    @GET
    @Path(Paths.FIND)
    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        try {
            return service.find();
        } catch (ServiceException e) {
            LOGGER.error("Finding gamblers failed", e);
            return null;
        }
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(@Valid Gambler gambler) {
        LOGGER.info("Register gambler {}", gambler.getName());
        try {
            service.register(gambler);
            return "Gambler " + gambler.getName() + " registered successfully";
        } catch (ServiceException e) {
            String error = "Registering gambler " + gambler.getName() + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }

    @PUT
    @Path(Paths.CHANGE)
    public String change(@Valid Gambler gambler) {
        LOGGER.info("Change gambler {}", gambler.getName());
        try {
            service.change(gambler);
            return "Gambler " + gambler.getName() + " changed successfully";
        } catch (ServiceException e) {
            String error = "Changing gambler " + gambler.getName() + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }

    @PUT
    @Path(Paths.ACTIVATE)
    public String activate(@NotNull @Min(1) @Pattern(regexp = "\\d+", message = "Not a number") String id) {
        LOGGER.info("Activate gambler with id {}", id);
        try {
            service.activate(Integer.parseInt(id));
            return "Gambler with id " + id + " activated successfully";
        } catch (NumberFormatException e) {
            return "Gambler id must be numeric";
        } catch (ServiceException e) {
            String error = "Activating gambler with id " + id + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }
}
