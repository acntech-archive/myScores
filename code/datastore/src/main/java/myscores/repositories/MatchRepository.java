package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.constants.NodeIndex;
import myscores.domain.Match;
import myscores.mappers.MatchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class MatchRepository extends AbstractRepository<Match> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private MatchMapper mapper;

    @Override
    public Match read(Object id) {
        LOGGER.info("Read match for id {}", id);
        return read(database, mapper, NodeIndex.MATCHES, id);
    }

    @Override
    public List<Match> find() {
        LOGGER.info("Find matches");
        return find(database, mapper, NodeIndex.MATCHES);
    }

    @Override
    public void create(Match match) {
        int id = nextId();
        LOGGER.info("Create match with id {}", id);
        match.setId(id);
        create(database, mapper, NodeIndex.MATCHES, match, id);
    }

    @Override
    public void update(Match match) {
        LOGGER.info("Update match with id {}", match.getId());
    }

    @Override
    public void delete(Object id) {
        LOGGER.info("Delete match with id {}", id);
        delete(database, mapper, NodeIndex.MATCHES, id);
    }

    @Override
    public int nextId() {
        return nextId(database, mapper, NodeIndex.MATCHES);
    }
}
