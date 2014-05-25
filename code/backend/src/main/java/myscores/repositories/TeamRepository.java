package myscores.repositories;

import myscores.domain.Team;

import java.util.HashMap;
import java.util.Map;

public class TeamRepository extends Repository<Team> {

    private static Map<String, Team> REPO = new HashMap<>();

    @Override
    protected Map<String, Team> getRepo() {
        return REPO;
    }
}
