package myscores.resources;

import myscores.constants.Paths;
import myscores.constants.ContentType;
import myscores.domain.Gambler;
import myscores.domain.Match;
import myscores.domain.Party;
import myscores.domain.Team;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;

@Path(Paths.DEMO)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class DemoResource {

    @GET
    @Path(Paths.GAMBLER)
    public Gambler gambler() {
        Gambler gambler = new Gambler();
        gambler.setId(1);
        gambler.setName("Demo Gambler");
        return gambler;
    }

    @GET
    @Path(Paths.PARTY)
    public Party party() {
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
    @Path(Paths.TEAM)
    public Team team() {
        Team team = new Team();
        team.setId(1);
        team.setName("Demo Team");
        team.setFifaRanking(10);
        return team;
    }

    @GET
    @Path(Paths.MATCH)
    public Match match() {
        Match match = new Match();
        match.setId(1);
        return match;
    }
}
