package myscores.repositories;

import myscores.domain.Match;

import java.util.HashMap;
import java.util.Map;

public class MatchRepository extends Repository<Match> {

    private static Map<String, Match> REPO = new HashMap<>();

    @Override
    protected Map<String, Match> getRepo() {
        return REPO;
    }
}
