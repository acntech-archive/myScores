package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Gambler;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GamblerMapper extends NodeMapper<Gambler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerMapper.class);

    @Override
    public Gambler map(Index<Node> index, int id) {
        Node node = index.get(Props.ID, id).getSingle();
        return map(node);
    }

    @Override
    public List<Gambler> mapAll(Index<Node> index) {
        List<Gambler> gamblers = new ArrayList<>();
        IndexHits<Node> nodes = index.query(Props.ID, Props.ALL);
        if (nodes != null) {
            LOGGER.debug("{} gamblers found", nodes.size());
            for (Node node : nodes) {
                Gambler gambler = map(node);
                if (gambler != null) {
                    gamblers.add(gambler);
                }
            }
        }
        return gamblers;
    }

    @Override
    public Gambler map(Node node) {
        if (node != null) {
            Gambler gambler = new Gambler();
            gambler.setId((Integer) node.getProperty(Props.ID));
            gambler.setName((String) node.getProperty(Props.NAME));
            gambler.setActive((Boolean) node.getProperty(Props.ACTIVE));
            return gambler;
        } else {
            return null;
        }
    }

    @Override
    public Node map(Node node, Gambler gambler) {
        if (node != null && gambler != null) {
            node.addLabel(createLabel(Gambler.class));
            node.setProperty(Props.ID, gambler.getId());
            node.setProperty(Props.NAME, gambler.getName());
            node.setProperty(Props.ACTIVE, gambler.isActive());
            return node;
        } else {
            return null;
        }
    }
}
