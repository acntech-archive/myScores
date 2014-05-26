package myscores.resources;

import myscores.Paths;
import myscores.domain.Gambler;
import myscores.services.GamblerService;
import myscores.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path(Paths.GAMBLER)
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
        try {
            return service.get(Integer.parseInt(id));
        } catch (ServiceException e) {
            LOGGER.error("Getting gambler with id " + id + " failed", e);
            return null;
        }
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
    public String register(Gambler gambler) {
        LOGGER.info("Register gambler");
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
    @Path(Paths.ACTIVATE)
    public String activate(String id) {
        LOGGER.info("Activate gambler");
        try {
            service.activate(Integer.parseInt(id));
            return "Gambler with id " + id + " activated successfully";
        } catch (ServiceException e) {
            String error = "Activating gambler with id " + id + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }
}
