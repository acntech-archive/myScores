package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Match;
import myscores.mappers.MatchMapper;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository extends Repository<Match> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private MatchMapper mapper;

    @Override
    public Match read(int id) {
        LOGGER.info("Read match for id {}", id);
        Match match = null;
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            if (node != null) {
                match = mapper.mapNode(node);
            } else {
                LOGGER.warn("Read failed. No match found for id {}", id);
            }
            tx.success();
        }
        return match;
    }

    @Override
    public List<Match> find() {
        LOGGER.info("Find matches");
        List<Match> matches = new ArrayList<>();
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            IndexHits<Node> nodes = index.query(Props.ID, Props.ALL);
            if (nodes != null) {
                for (Node node : nodes) {
                    matches.add(mapper.mapNode(node));
                }
            } else {
                LOGGER.warn("Find failed. No matches found");
            }
            tx.success();
        }
        return matches;
    }

    @Override
    public void create(Match match) {
        LOGGER.info("Create match with id {}", match.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.MATCHES_INDEX);
            if (index.get(Props.ID, match.getId()).getSingle() == null) {
                Node node = mapper.mapNode(database.createNode(), match);
                index.add(node, Props.ID, match.getId());
            } else {
                LOGGER.warn("Create failed. Match with id {} already exists", match.getId());
            }
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
            if (node != null) {
                index.remove(node, Props.ID, node.getProperty(Props.ID));
                node.delete();
            } else {
                LOGGER.warn("Delete failed. No match found for id {}", id);
            }
            tx.success();
        }
    }
}
