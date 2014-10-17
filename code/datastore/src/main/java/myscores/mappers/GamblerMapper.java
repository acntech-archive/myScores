package myscores.mappers;

import myscores.constants.Key;
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
            LOGGER.debug("Mapping node to domain object");
            Gambler gambler = new Gambler();
            gambler.setId(getIntProperty(node, Key.ID));
            gambler.setUsername(getStringProperty(node, Key.USERNAME));
            gambler.setFirstName(safeGetStringProperty(node, Key.FIRST_NAME));
            gambler.setMiddleName(safeGetStringProperty(node, Key.MIDDLE_NAME));
            gambler.setLastName(safeGetStringProperty(node, Key.LAST_NAME));
            gambler.setActive(getBooleanProperty(node, Key.ACTIVE));
            return gambler;
        } else {
            LOGGER.debug("Node is null or has wrong label");
            return null;
        }
    }

    @Override
    public Node map(Node node, Gambler gambler) {
        if (node != null && gambler != null) {
            LOGGER.debug("Mapping domain object to node");
            node.addLabel(createLabel());
            setProperty(node, Key.ID, gambler.getId());
            setProperty(node, Key.USERNAME, gambler.getUsername());
            setProperty(node, Key.FIRST_NAME, gambler.getFirstName());
            setProperty(node, Key.MIDDLE_NAME, gambler.getMiddleName());
            setProperty(node, Key.LAST_NAME, gambler.getLastName());
            setProperty(node, Key.ACTIVE, gambler.isActive());
            return node;
        } else {
            LOGGER.debug("Node or domain object is null");
            return null;
        }
    }

    @Override
    public Key getDefaultKey() {
        return Key.ID;
    }

    @Override
    public Label createLabel() {
        return createLabel(Gambler.class);
    }
}
