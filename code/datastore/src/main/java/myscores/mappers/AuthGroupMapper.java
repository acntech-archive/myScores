package myscores.mappers;

import myscores.constants.Props;
import myscores.domain.AuthGroup;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthGroupMapper extends NodeMapper<AuthGroup> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthGroupMapper.class);

    @Override
    public AuthGroup map(Node node) {
        if (node != null && hasLabel(node, createLabel())) {
            LOGGER.debug("Mapping node to domain object");
            AuthGroup authGroup = new AuthGroup();
            authGroup.setName(getStringProperty(node, Props.NAME));
            return authGroup;
        } else {
            LOGGER.debug("Node is null or has wrong label");
            return null;
        }
    }

    @Override
    public Node map(Node node, AuthGroup authGroup) {
        if (node != null && authGroup != null) {
            LOGGER.debug("Mapping domain object to node");
            node.addLabel(createLabel());
            node.setProperty(Props.NAME, authGroup.getName());
            return node;
        } else {
            LOGGER.debug("Node or domain object is null");
            return null;
        }
    }

    @Override
    public String getDefaultKey() {
        return Props.USERNAME;
    }

    @Override
    public Label createLabel() {
        return createLabel(AuthGroup.class);
    }
}
