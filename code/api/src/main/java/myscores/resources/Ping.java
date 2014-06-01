package myscores.resources;

import myscores.constants.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(Paths.PING)
public class Ping {

    @GET
    public String ping() {
        return "Hello!";
    }
}
