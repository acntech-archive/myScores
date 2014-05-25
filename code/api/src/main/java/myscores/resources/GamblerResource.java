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
    @Path(Paths.GET)
    public Gambler get(@PathParam(Paths.ID) String id) {
        LOGGER.info("Get gambler with id {}", id);
        return service.get(Integer.parseInt(id));
    }

    @GET
    @Path(Paths.FIND)
    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        return service.find();
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(Gambler gambler) {
        LOGGER.info("Register gambler");
        if (service.register(gambler)) {
            return "Gambler '" + gambler.getName() + "' registered successfully";
        } else {
            return "Gambler '" + gambler.getName() + "' could not be registered";
        }
    }

    @PUT
    @Path(Paths.ACTIVATE)
    public String activate(String id) {
        LOGGER.info("Activate gambler");
        if (service.activate(Integer.parseInt(id))) {
            return "Gambler with id " + id + " activated successfully";
        } else {
            return "Gambler with id " + id + " could not activated";
        }
    }
}
