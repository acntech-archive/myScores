package myscores.mappers;

import myscores.constants.Key;
import myscores.domain.Match;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchMapper extends NodeMapper<Match> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchMapper.class);

    @Override
    public Match map(Node node) {
        if (node != null && hasLabel(node, createLabel())) {
            LOGGER.debug("Mapping node to domain object");
            Match match = new Match();
            match.setId(getIntProperty(node, Key.ID));
            return match;
        } else {
            LOGGER.debug("Node is null or has wrong label");
            return null;
        }
    }

    @Override
    public Node map(Node node, Match match) {
        if (node != null && match != null) {
            LOGGER.debug("Mapping domain object to node");
            node.addLabel(createLabel());
            setProperty(node, Key.ID, match.getId());
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
        return createLabel(Match.class);
    }
}
