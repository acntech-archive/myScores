package myscores.database;

public interface Props {

    String DATABASE_ROOT = "/tmp";
    String DATABASE_NAME = "myscores-neo4j";

    String PARTIES_INDEX = "parties";
    String GAMBLERS_INDEX = "gamblers";
    String MATCHES_INDEX = "matches";
    String TEAMS_INDEX = "teams";

    String ID = "id";
    String NAME = "name";
    String ACTIVE = "active";
    String SINCE = "since";
    String RANK = "rank";

    String ALL = "*";
}
