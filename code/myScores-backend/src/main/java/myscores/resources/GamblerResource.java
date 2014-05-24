package myscores.resources;

import myscores.domain.Gambler;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("gambler")
public class GamblerResource {
    
    @GET
    public List<Gambler> get() {
        System.out.println("GET");
        
        List<Gambler> gamblers = new ArrayList<>();
        Gambler gambler = new Gambler();
        gambler.setId(1);
        gambler.setName("TestGambler");
        return gamblers;
    }
    
}
