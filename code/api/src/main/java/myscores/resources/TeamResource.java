package myscores.resources;

import myscores.Paths;
import myscores.domain.Team;
import myscores.services.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path(Paths.TEAM)
public class TeamResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamResource.class);

    @Inject
    private TeamService service;

    @GET
    @Path(Paths.GET)
    public Team get(@PathParam(Paths.ID) String id) {
        LOGGER.info("Get team with id {}", id);
        return service.get(Integer.parseInt(id));
    }

    @GET
    @Path(Paths.FIND)
    public List<Team> find() {
        LOGGER.info("Find teams");
        return service.find();
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(Team team) {
        LOGGER.info("Register team");
        if (service.register(team)) {
            return "Team '" + team.getName() + "' registered successfully";
        } else {
            return "Team '" + team.getName() + "' could not registered";
        }
    }
}
