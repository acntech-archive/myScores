package myscores.resources;

import myscores.Paths;
import myscores.domain.Gambler;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path(Paths.GAMBLER)
public class GamblerResource {

    @GET
    @Path(Paths.LIST)
    public List<Gambler> list() {
        System.out.println("LIST");

        List<Gambler> gamblers = new ArrayList<>();
        Gambler gambler = new Gambler();
        gambler.setId(1);
        gambler.setName("TestGambler");
        gamblers.add(gambler);
        return gamblers;
    }

    @POST
    @Path(Paths.REGISTER)
    public String register(Gambler gambler) {
        System.out.println("REGISTER");
        return "Success";
    }

    @PUT
    @Path(Paths.ACTIVATE)
    public String activate(String name) {
        System.out.println("ACTIVATE");
        return "Success";
    }
}
