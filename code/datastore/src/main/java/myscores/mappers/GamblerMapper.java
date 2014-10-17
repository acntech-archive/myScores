package myscores.mappers;

import myscores.constants.Props;
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
            gambler.setId(getIntProperty(node, Props.ID));
            gambler.setUsername(getStringProperty(node, Props.USERNAME));
            gambler.setFirstName(safeGetStringProperty(node, Props.FIRST_NAME));
            gambler.setMiddleName(safeGetStringProperty(node, Props.MIDDLE_NAME));
            gambler.setLastName(safeGetStringProperty(node, Props.LAST_NAME));
            gambler.setActive(getBooleanProperty(node, Props.ACTIVE));
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
            node.setProperty(Props.ID, gambler.getId());
            node.setProperty(Props.USERNAME, gambler.getUsername());
            safeSetProperty(node, Props.FIRST_NAME, gambler.getFirstName());
            safeSetProperty(node, Props.MIDDLE_NAME, gambler.getMiddleName());
            safeSetProperty(node, Props.LAST_NAME, gambler.getLastName());
            node.setProperty(Props.ACTIVE, gambler.isActive());
            return node;
        } else {
            LOGGER.debug("Node or domain object is null");
            return null;
        }
    }

    @Override
    public String getDefaultKey() {
        return Props.ID;
    }

    @Override
    public Label createLabel() {
        return createLabel(Gambler.class);
    }
}
