package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Gambler;
import myscores.mappers.GamblerMapper;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GamblerRepository extends Repository<Gambler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Inject
    private GamblerMapper mapper;

    @Override
    public Gambler read(int id) {
        LOGGER.info("Read gambler for id {}", id);
        Gambler gambler = null;
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            if (node != null) {
                gambler = mapper.mapNode(node);
            } else {
                LOGGER.warn("Read failed. No gambler found for id {}", id);
            }
            tx.success();
        }
        return gambler;
    }

    @Override
    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        List<Gambler> gamblers = new ArrayList<>();
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            IndexHits<Node> nodes = index.query(Props.ID, Props.ALL);
            if (nodes != null) {
                for (Node node : nodes) {
                    gamblers.add(mapper.mapNode(node));
                }
            } else {
                LOGGER.warn("Find failed. No gamblers found");
            }
            tx.success();
        }
        return gamblers;
    }

    @Override
    public void create(Gambler gambler) {
        LOGGER.info("Create gambler with id {}", gambler.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            if (index.get(Props.ID, gambler.getId()).getSingle() == null) {
                Node node = mapper.mapNode(database.createNode(), gambler);
                index.add(node, Props.ID, gambler.getId());
                index.add(node, Props.NAME, gambler.getName());
            } else {
                LOGGER.warn("Create failed. Gambler with id {} already exists", gambler.getId());
            }
            tx.success();
        }
    }

    @Override
    public void update(Gambler gambler) {
        LOGGER.info("Update gambler with id {}", gambler.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = index.get(Props.ID, gambler.getId()).getSingle();
            if (node != null) {
                node.setProperty(Props.NAME, gambler.getName());
                node.setProperty(Props.ACTIVE, gambler.isActive());
            } else {
                LOGGER.warn("Update failed. No gambler found for id {}", gambler.getId());
            }
            tx.success();
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete gambler with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            if (node != null) {
                index.remove(node, Props.ID, node.getProperty(Props.ID));
                index.remove(node, Props.NAME, node.getProperty(Props.NAME));
                node.delete();
            } else {
                LOGGER.warn("Delete failed. No gambler found for id {}", id);
            }
            tx.success();
        }
    }


}
