package myscores.repositories;

import myscores.constants.Key;
import myscores.constants.NodeIndex;
import myscores.database.GraphDatabase;
import myscores.domain.Gambler;
import myscores.mappers.GamblerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static myscores.mappers.Mapper.keyValue;

@Stateless
public class GamblerRepository extends AbstractRepository<Gambler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerRepository.class);

    @Inject
    private GraphDatabase database;

    @Inject
    private GamblerMapper mapper;

    @Override
    public Gambler read(Object id) {
        LOGGER.info("Read gambler for id {}", id);
        return read(database, mapper, NodeIndex.GAMBLERS, id);
    }

    @Override
    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        return find(database, mapper, NodeIndex.GAMBLERS);
    }

    @Override
    public void create(Gambler gambler) {
        int id = nextId();
        LOGGER.info("Create gambler with id {}", id);
        gambler.setId(id);
        create(database, mapper, NodeIndex.GAMBLERS, gambler, id, keyValue(Key.USERNAME, gambler.getUsername()));
    }

    @Override
    public void update(Gambler gambler) {
        LOGGER.info("Update gambler with id {}", gambler.getId());
        update(database, mapper, NodeIndex.GAMBLERS, gambler.getId(), keyValue(Key.USERNAME, gambler.getUsername()), keyValue(Key.ACTIVE, gambler.isActive()));
    }

    @Override
    public void delete(Object id) {
        LOGGER.info("Delete gambler with id {}", id);
        delete(database, mapper, NodeIndex.GAMBLERS, id, Key.USERNAME);
    }

    @Override
    public int nextId() {
        return nextId(database, mapper, NodeIndex.GAMBLERS);
    }
}
