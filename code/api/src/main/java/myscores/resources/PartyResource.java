package myscores.resources;

import myscores.Paths;
import myscores.domain.Gambler;
import myscores.domain.Party;
import myscores.services.PartyService;
import myscores.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Path(Paths.PARTY)
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
        try {
            return service.get(Integer.parseInt(id));
        } catch (ServiceException e) {
            LOGGER.error("Getting party with id " + id + " failed", e);
            return null;
        }
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
    public String register(Party party) {
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

    @POST
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
}
