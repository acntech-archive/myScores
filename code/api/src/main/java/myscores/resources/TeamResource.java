package myscores.resources;

import myscores.constants.ContentType;
import myscores.constants.Params;
import myscores.constants.Paths;
import myscores.domain.Team;
import myscores.services.TeamService;
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

@Path(Paths.TEAMS)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class TeamResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamResource.class);

    @Inject
    private TeamService service;

    @GET
    @Path(Paths.ID)
    public Team get(@PathParam(Params.ID) String id) {
        LOGGER.info("Get team with id {}", id);
        return service.get(parseInt(id));
    }

    @GET
    public List<Team> get() {
        LOGGER.info("Get teams");
        return service.find();
    }

    @POST
    public String post(Team team) {
        LOGGER.info("Post team");
        service.register(team);
        return "Team " + team.getName() + " posted successfully";
    }

    @PUT
    public String put(Team team) {
        LOGGER.info("Put team");
        service.change(team);
        return "Team " + team.getName() + " put successfully";
    }

    @DELETE
    @Path(Paths.ID)
    public String delete(@PathParam(Params.ID) String id) {
        LOGGER.info("Delete team with id {}", id);
        service.delete(parseInt(id));
        return "Team with id " + id + " deleted successfully";
    }
}
