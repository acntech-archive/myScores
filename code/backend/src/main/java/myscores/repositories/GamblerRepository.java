package myscores.repositories;

import myscores.database.LocalGraphDatabase;
import myscores.database.Props;
import myscores.domain.Gambler;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class GamblerRepository extends Repository<Gambler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerRepository.class);

    @Inject
    private LocalGraphDatabase database;

    @Override
    public Gambler read(int id) {
        LOGGER.info("Read gambler for id {}", id);
        Gambler gambler;
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            gambler = new Gambler();
            gambler.setId((Integer) node.getProperty(Props.ID));
            gambler.setName((String) node.getProperty(Props.NAME));
            gambler.setActive((Boolean) node.getProperty(Props.ACTIVE));
            tx.success();
        }
        return gambler;
    }

    @Override
    public List<Gambler> find() {
        LOGGER.info("Find gamblers");
        return null;
    }

    @Override
    public void create(Gambler gambler) {
        LOGGER.info("Create gambler with id {}", gambler.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = database.createNode();
            node.setProperty(Props.ID, gambler.getId());
            node.setProperty(Props.NAME, gambler.getName());
            node.setProperty(Props.ACTIVE, gambler.isActive());
            index.add(node, Props.ID, gambler.getId());
            index.add(node, Props.NAME, gambler.getName());
            tx.success();
        }
    }

    @Override
    public void update(Gambler gambler) {
        LOGGER.info("Update gambler with id {}", gambler.getId());
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = index.get(Props.ID, gambler.getId()).getSingle();
            node.setProperty(Props.NAME, gambler.getName());
            node.setProperty(Props.ACTIVE, gambler.isActive());
            tx.success();
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.info("Delete gambler with id {}", id);
        try (Transaction tx = database.startTransaction()) {
            Index<Node> index = database.getNodeIndex(Props.GAMBLERS_INDEX);
            Node node = index.get(Props.ID, id).getSingle();
            index.remove(node, Props.ID, node.getProperty(Props.ID));
            index.remove(node, Props.NAME, node.getProperty(Props.NAME));
            node.delete();
            tx.success();
        }
    }
}
