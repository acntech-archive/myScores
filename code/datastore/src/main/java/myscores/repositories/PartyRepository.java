package myscores.repositories;

import myscores.constants.Key;
import myscores.constants.NodeIndex;
import myscores.database.GraphDatabase;
import myscores.datetime.DateTimeParser;
import myscores.domain.Party;
import myscores.mappers.PartyMapper;
import myscores.relationships.ForGambler;
import org.joda.time.DateTime;
import org.neo4j.graphdb.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

import static myscores.mappers.Mapper.keyValue;

@Stateless
public class PartyRepository extends AbstractRepository<Party> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyRepository.class);

    @Inject
    private GraphDatabase database;

    @Inject
    private PartyMapper mapper;

    @Override
    public Party read(Object id) {
        LOGGER.info("Read party for id {}", id);
        return read(database, mapper, NodeIndex.PARTIES, id);
    }

    @Override
    public List<Party> find() {
        LOGGER.info("Find parties");
        return find(database, mapper, NodeIndex.PARTIES);
    }

    @Override
    public void create(Party party) {
        int id = nextId();
        LOGGER.info("Create party with id {}", id);
        party.setId(id);
        create(database, mapper, NodeIndex.PARTIES, party, id);
    }

    @Override
    public void update(Party party) {
        LOGGER.info("Update party with id {}", party.getId());
        update(database, mapper, NodeIndex.PARTIES, party.getId(), keyValue(Key.NAME, party.getName()));
    }

    @Override
    public void delete(Object id) {
        LOGGER.info("Delete party with id {}", id);
        delete(database, mapper, NodeIndex.PARTIES, id);
    }

    @Override
    public void add(Object partyId, Object gamblerId) {
        LOGGER.info("Adding gambler with id {} to party with id {}", partyId);
        add(database, mapper, NodeIndex.PARTIES, NodeIndex.GAMBLERS, partyId, gamblerId, ForGambler.BELONGS_TO, keyValue(Key.SINCE, DateTimeParser.print(DateTime.now())));
    }

    @Override
    public void remove(Object partyId, Object gamblerId) {
        LOGGER.info("Remove gambler with id {} from party with id {}", partyId);
        remove(database, mapper, NodeIndex.PARTIES, NodeIndex.GAMBLERS, partyId, gamblerId, ForGambler.BELONGS_TO, Direction.OUTGOING);
    }

    @Override
    public int nextId() {
        return nextId(database, mapper, NodeIndex.PARTIES);
    }
}
