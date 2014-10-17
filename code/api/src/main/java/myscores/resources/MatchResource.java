package myscores.resources;

import myscores.constants.ContentType;
import myscores.constants.Params;
import myscores.constants.Paths;
import myscores.domain.Match;
import myscores.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

import static myscores.util.ParseUtil.parseInt;

@Path(Paths.MATCHES)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class MatchResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchResource.class);

    @Inject
    private MatchService service;

    @GET
    @Path(Paths.ID)
    public Match get(@PathParam(Params.ID) String id) {
        LOGGER.info("Get match with id {}", id);
        return service.get(parseInt(id));
    }

    @GET
    public List<Match> get() {
        LOGGER.info("Get matches");
        return service.find();
    }

    @POST
    public String post(Match match) {
        LOGGER.info("Post match");
        service.register(match);
        return "Match registered successfully";
    }

    @DELETE
    @Path(Paths.ID)
    public String delete(@PathParam(Params.ID) String id) {
        LOGGER.info("Delete match with id {}", id);
        service.delete(parseInt(id));
        return "Match with id " + id + " deleted successfully";
    }
}
