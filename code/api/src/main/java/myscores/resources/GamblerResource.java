package myscores.resources;

import myscores.Paths;
import myscores.domain.Gambler;
import myscores.rules.GamblerService;
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
    @Path(Paths.GET_GAMBLER)
    public Gambler get(@PathParam(Paths.NAME) String name) {
        LOGGER.info("GET");
        return service.get(name);
    }

    @GET
    @Path(Paths.FIND_GAMBLERS)
    public List<Gambler> find() {
        LOGGER.info("FIND");
        return service.list();
    }

    @POST
    @Path(Paths.REGISTER_GAMBLER)
    public String register(Gambler gambler) {
        LOGGER.info("REGISTER");
        return service.register(gambler);
    }

    @PUT
    @Path(Paths.ACTIVATE_GAMBLER)
    public String activate(String name) {
        LOGGER.info("ACTIVATE");
        return service.activate(name);
    }
}
