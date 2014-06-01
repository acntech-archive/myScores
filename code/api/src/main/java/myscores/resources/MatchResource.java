package myscores.resources;

import myscores.Paths;
import myscores.constants.ContentType;
import myscores.domain.Match;
import myscores.services.MatchService;
import myscores.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path(Paths.MATCH)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class MatchResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchResource.class);

    @Inject
    private MatchService service;

    @GET
    public Match root() {
        Match match = new Match();
        match.setId(1);
        return match;
    }

    @GET
    @Path(Paths.GET)
    public Match get(@PathParam(Paths.ID) String id) {
        LOGGER.info("Get match with id {}", id);
        Match match = null;
        try {
            match = service.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            LOGGER.error("Gambler id must be numeric");
        } catch (ServiceException e) {
            LOGGER.error("Getting match with id " + id + " failed", e);
        }
        return match;
    }

    @GET
    @Path(Paths.FIND)
    public List<Match> find() {
        LOGGER.info("Find matches");
        try {
            return service.find();
        } catch (ServiceException e) {
            LOGGER.error("Finding matches failed", e);
            return null;
        }
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(@Valid Match match) {
        LOGGER.info("Register match");
        try {
            service.register(match);
            return "Match registered successfully";
        } catch (ServiceException e) {
            String error = "Registering match failed";
            LOGGER.error(error, e);
            return error;
        }
    }

    @DELETE
    @Path(Paths.DELETE)
    public String delete(String id) {
        LOGGER.info("Deleting match with id {}", id);
        try {
            service.delete(Integer.parseInt(id));
            return "Match with id " + id + " deleted successfully";
        } catch (NumberFormatException e) {
            return "Match id must be numeric";
        } catch (ServiceException e) {
            String error = "Deletion of match with id " + id + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }
}
