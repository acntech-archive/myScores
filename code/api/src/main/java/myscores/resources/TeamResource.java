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

@Path(Paths.MATCH)
public class TeamResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamResource.class);

    @Inject
    private TeamService service;

    @GET
    @Path(Paths.GET_ID)
    public Team get(@PathParam(Paths.NAME) String id) {
        LOGGER.info("GET");
        return service.get(id);
    }

    @GET
    @Path(Paths.FIND)
    public List<Team> find() {
        LOGGER.info("FIND");
        return service.find();
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(Team team) {
        LOGGER.info("REGISTER");
        if (service.register(team)) {
            return "Team '" + team.getName() + "' registered successfully";
        } else {
            return "Team '" + team.getName() + "' could not registered";
        }
    }
}
