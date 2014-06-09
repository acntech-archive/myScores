package myscores.resources;

import myscores.constants.ContentType;
import myscores.constants.Params;
import myscores.constants.Paths;
import myscores.domain.Gambler;
import myscores.services.GamblerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path(Paths.GAMBLERS)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class GamblerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerResource.class);

    @Inject
    private GamblerService service;

    @GET
    @Path(Paths.ID)
    public Gambler get(@PathParam(Params.ID) String id) {
        LOGGER.info("Get gambler with id {}", id);
        return service.get(Integer.parseInt(id));
    }

    @GET
    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        return service.find();
    }

    @POST
    public String register(Gambler gambler) {
        LOGGER.info("Register gambler {}", gambler.getUsername());
        service.register(gambler);
        return "Gambler " + gambler.getUsername() + " registered successfully";
    }

    @PUT
    public String change(Gambler gambler) {
        LOGGER.info("Change gambler {}", gambler.getUsername());
        service.change(gambler);
        return "Gambler " + gambler.getUsername() + " changed successfully";
    }

    @DELETE
    @Path(Paths.DELETE)
    public String delete(@PathParam(Params.ID) String id) {
        LOGGER.info("Deleting gambler with id {}", id);
        service.delete(Integer.parseInt(id));
        return "Gambler with id " + id + " deleted successfully";
    }

    @PUT
    @Path(Paths.ACTIVATE)
    public String activate(@PathParam(Params.ID) String id) {
        LOGGER.info("Activate gambler with id {}", id);
        service.activate(Integer.parseInt(id));
        return "Gambler with id " + id + " activated successfully";
    }
}
