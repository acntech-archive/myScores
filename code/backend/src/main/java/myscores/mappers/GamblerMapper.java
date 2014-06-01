package myscores.mappers;

import myscores.database.Props;
import myscores.domain.Gambler;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamblerMapper extends NodeMapper<Gambler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GamblerMapper.class);

    @Override
    public Gambler map(Node node) {
        if (node != null && hasLabel(node, createLabel())) {
            Gambler gambler = new Gambler();
            gambler.setId(getIdProperty(node));
            gambler.setName(getNameProperty(node));
            gambler.setActive(getBooleanProperty(node, Props.ACTIVE));
            return gambler;
        } else {
            return null;
        }
    }

    @Override
    public Node map(Node node, Gambler gambler) {
        if (node != null && gambler != null) {
            node.addLabel(createLabel());
            node.setProperty(Props.ID, gambler.getId());
            node.setProperty(Props.NAME, gambler.getName());
            node.setProperty(Props.ACTIVE, gambler.isActive());
            return node;
        } else {
            return null;
        }
    }

    @Override
    public Label createLabel() {
        return createLabel(Gambler.class);
    }
}
