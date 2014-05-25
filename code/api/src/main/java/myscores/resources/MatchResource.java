package myscores.resources;

import myscores.Paths;
import myscores.domain.Match;
import myscores.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path(Paths.MATCH)
public class MatchResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchResource.class);

    @Inject
    private MatchService service;

    @GET
    @Path(Paths.GET_ID)
    public Match get(@PathParam(Paths.NAME) String id) {
        LOGGER.info("GET");
        return service.get(id);
    }

    @GET
    @Path(Paths.FIND)
    public List<Match> find() {
        LOGGER.info("FIND");
        return service.find();
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(Match match) {
        LOGGER.info("REGISTER");
        if (service.register(match)) {
            return "Match '" + match.getId() + "' registered successfully";
        } else {
            return "Match '" + match.getId() + "' could not registered";
        }
    }
}
