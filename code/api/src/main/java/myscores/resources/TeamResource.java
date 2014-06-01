package myscores.resources;

import myscores.constants.Params;
import myscores.constants.Paths;
import myscores.constants.ContentType;
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
        return service.get(Integer.parseInt(id));
    }

    @GET
    public List<Team> find() {
        LOGGER.info("Find teams");
        return service.find();
    }

    @POST
    public String register(Team team) {
        LOGGER.info("Register team");
        service.register(team);
        return "Team " + team.getName() + " registered successfully";
    }

    @PUT
    public String change(Team team) {
        LOGGER.info("Change team");
        service.change(team);
        return "Team " + team.getName() + " changed successfully";
    }

    @DELETE
    @Path(Paths.DELETE)
    public String delete(@PathParam(Params.ID) String id) {
        LOGGER.info("Deleting team with id {}", id);
        service.delete(Integer.parseInt(id));
        return "Team with id " + id + " deleted successfully";
    }
}
