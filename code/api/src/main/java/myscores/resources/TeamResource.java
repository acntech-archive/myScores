package myscores.resources;

import myscores.Paths;
import myscores.constants.ContentType;
import myscores.domain.Team;
import myscores.services.ServiceException;
import myscores.services.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path(Paths.TEAM)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class TeamResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamResource.class);

    @Inject
    private TeamService service;

    @GET
    public Team root() {
        Team team = new Team();
        team.setId(1);
        team.setName("Demo Team");
        return team;
    }

    @GET
    @Path(Paths.GET)
    public Team get(@PathParam(Paths.ID) String id) {
        LOGGER.info("Get team with id {}", id);
        Team team = null;
        try {
            team = service.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            LOGGER.error("Gambler id must be numeric");
        } catch (ServiceException e) {
            LOGGER.error("Getting team with id " + id + " failed", e);
        }
        return team;
    }

    @GET
    @Path(Paths.FIND)
    public List<Team> find() {
        LOGGER.info("Find teams");
        try {
            return service.find();
        } catch (ServiceException e) {
            LOGGER.error("Finding teams failed", e);
            return null;
        }
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(@Valid Team team) {
        LOGGER.info("Register team");
        try {
            service.register(team);
            return "Team " + team.getName() + " registered successfully";
        } catch (ServiceException e) {
            String error = "Registering team " + team.getName() + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }

    @PUT
    @Path(Paths.CHANGE)
    public String change(@Valid Team team) {
        LOGGER.info("Change team");
        try {
            service.change(team);
            return "Team " + team.getName() + " changed successfully";
        } catch (ServiceException e) {
            String error = "Changing team " + team.getName() + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }
}
