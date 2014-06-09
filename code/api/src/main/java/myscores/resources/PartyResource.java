package myscores.resources;

import myscores.constants.ContentType;
import myscores.constants.Params;
import myscores.constants.Paths;
import myscores.domain.Party;
import myscores.services.PartyService;
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

@Path(Paths.PARTIES)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class PartyResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyResource.class);

    @Inject
    private PartyService service;

    @GET
    @Path(Paths.ID)
    public Party get(@PathParam(Params.ID) String id) {
        LOGGER.info("Get party with id {}", id);
        return service.get(Integer.parseInt(id));
    }

    @GET
    public List<Party> find() {
        LOGGER.info("Find parties");
        return service.find();
    }

    @POST
    public String register(Party party) {
        LOGGER.info("Register party");
        service.register(party);
        return "Party " + party.getName() + " registered successfully";
    }

    @PUT
    public String change(Party party) {
        LOGGER.info("Change party");
        service.change(party);
        return "Party " + party.getName() + " changed successfully";
    }

    @DELETE
    @Path(Paths.DELETE)
    public String delete(@PathParam(Params.ID) String id) {
        LOGGER.info("Deleting party with id {}", id);
        service.delete(Integer.parseInt(id));
        return "Party with id " + id + " deleted successfully";
    }

    @PUT
    @Path(Paths.ADD)
    public String add(@PathParam(Params.ID) String id, List<Integer> gamblers) {
        LOGGER.info("Adding to party with id {}", id);
        service.add(Integer.parseInt(id), gamblers);
        return "Added gamblers to party with id " + id + " successfully";
    }

    @DELETE
    @Path(Paths.REMOVE)
    public String remove(@PathParam(Params.ID) String id, List<Integer> gamblers) {
        LOGGER.info("Removing from party with id {}", id);
        service.remove(Integer.parseInt(id), gamblers);
        return "Removed gamblers from party with id " + id + " successfully";
    }
}
