package myscores;

public interface Paths {

    String API_ROOT = "/api";
    String PING = "/ping";

    String GAMBLER = "/gambler";
    String GET_GAMBLER = "/get/{name}";
    String FIND_GAMBLERS = "/find";
    String REGISTER_GAMBLER = "/register";
    String ACTIVATE_GAMBLER = "/activate";

    String MATCH = "/match";

    String NAME = "{name}";
}
