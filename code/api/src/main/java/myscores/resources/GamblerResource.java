package myscores.resources;

import myscores.Paths;
import myscores.domain.Gambler;
import myscores.services.GamblerService;
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
    @Path(Paths.GET_NAME)
    public Gambler get(@PathParam(Paths.NAME) String name) {
        LOGGER.info("GET");
        return service.get(name);
    }

    @GET
    @Path(Paths.FIND)
    public List<Gambler> find() {
        LOGGER.info("FIND");
        return service.list();
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(Gambler gambler) {
        LOGGER.info("REGISTER");
        if (service.register(gambler)) {
            return "Gambler '" + gambler.getName() + "' registered successfully";
        } else {
            return "Gambler '" + gambler.getName() + "' could not registered";
        }
    }

    @PUT
    @Path(Paths.ACTIVATE)
    public String activate(String name) {
        LOGGER.info("ACTIVATE");
        if (service.activate(name)) {
            return "Gambler '" + name + "' activated successfully";
        } else {
            return "Gambler '" + name + "' could not activated";
        }
    }
}
