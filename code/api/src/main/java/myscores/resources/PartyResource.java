package myscores.resources;

import myscores.Paths;
import myscores.constants.ContentType;
import myscores.domain.Gambler;
import myscores.domain.Party;
import myscores.services.PartyService;
import myscores.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path(Paths.PARTY)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class PartyResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyResource.class);

    @Inject
    private PartyService service;

    @GET
    public Party root() {
        Party party = new Party();
        party.setId(1);
        party.setName("Demo Party");
        party.setGamblers(new ArrayList<Gambler>());
        Gambler gambler = new Gambler();
        gambler.setId(1);
        gambler.setName("Demo Gambler");
        party.getGamblers().add(gambler);
        return party;
    }

    @GET
    @Path(Paths.GET)
    public Party get(@PathParam(Paths.ID) String id) {
        LOGGER.info("Get party with id {}", id);
        Party party = null;
        try {
            party = service.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            LOGGER.error("Gambler id must be numeric");
        } catch (ServiceException e) {
            LOGGER.error("Getting party with id " + id + " failed", e);
        }
        return party;
    }

    @GET
    @Path(Paths.FIND)
    public List<Party> find() {
        LOGGER.info("Find parties");
        try {
            return service.find();
        } catch (ServiceException e) {
            LOGGER.error("Finding parties failed", e);
            return null;
        }
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(@Valid Party party) {
        LOGGER.info("Register party");
        try {
            service.register(party);
            return "Party " + party.getName() + " registered successfully";
        } catch (ServiceException e) {
            String error = "Registering party " + party.getName() + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }

    @PUT
    @Path(Paths.CHANGE)
    public String change(@Valid Party party) {
        LOGGER.info("Change party");
        try {
            service.change(party);
            return "Party " + party.getName() + " changed successfully";
        } catch (ServiceException e) {
            String error = "Changing party " + party.getName() + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }

    @DELETE
    @Path(Paths.DELETE)
    public String delete(String id) {
        LOGGER.info("Deleting party with id {}", id);
        try {
            service.delete(Integer.parseInt(id));
            return "Party with id " + id + " deleted successfully";
        } catch (NumberFormatException e) {
            return "Party id must be numeric";
        } catch (ServiceException e) {
            String error = "Deletion of party with id " + id + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }

    @PUT
    @Path(Paths.ADD)
    public String add(Party party) {
        LOGGER.info("Adding to party with id {}", party.getId());
        try {
            service.add(party);
            return "Added gamblers to party " + party.getName() + " successfully";
        } catch (ServiceException e) {
            String error = "Adding gamblers to party " + party.getName() + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }

    @DELETE
    @Path(Paths.REMOVE)
    public String remove(Party party) {
        LOGGER.info("Removing from party with id {}", party.getId());
        try {
            service.remove(party);
            return "Removed gamblers to party " + party.getName() + " successfully";
        } catch (ServiceException e) {
            String error = "Adding gamblers to party " + party.getName() + " failed";
            LOGGER.error(error, e);
            return error;
        }
    }
}
