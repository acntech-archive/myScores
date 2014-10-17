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

import static myscores.util.ParseUtil.parseInt;

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
        return service.get(parseInt(id));
    }

    @GET
    public List<Party> get() {
        LOGGER.info("Get parties");
        return service.find();
    }

    @POST
    public String post(Party party) {
        LOGGER.info("Post party");
        service.register(party);
        return "Party " + party.getName() + " registered successfully";
    }

    @PUT
    public String put(Party party) {
        LOGGER.info("Put party");
        service.change(party);
        return "Party " + party.getName() + " changed successfully";
    }

    @DELETE
    @Path(Paths.ID)
    public String delete(@PathParam(Params.ID) String id) {
        LOGGER.info("Delete party with id {}", id);
        service.delete(parseInt(id));
        return "Party with id " + id + " deleted successfully";
    }
}
