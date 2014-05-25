package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Match;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class MatchRepository extends Repository<Match> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Override
    public Match read(int id) {
        LOGGER.info("Read match for id {}", id);
        Match match;
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            match = new Match();
            match.setId((Integer) node.getProperty(Props.ID));
            tx.success();
        }
        return match;
    }

    @Override
    public List<Match> find() {
        LOGGER.info("Find matches");
        return null;
    }

    @Override
    public void create(Match match) {
        LOGGER.info("Create match with id {}", match.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            Node node = database.createNode();
            node.setProperty(Props.ID, match.getId());
            index.add(node, Props.ID, match.getId());
            tx.success();
        }
    }

    @Override
    public void update(Match match) {
        LOGGER.info("Update match with id {}", match.getId());
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete match with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            index.remove(node, Props.ID, node.getProperty(Props.ID));
            node.delete();
            tx.success();
        }
    }
}
