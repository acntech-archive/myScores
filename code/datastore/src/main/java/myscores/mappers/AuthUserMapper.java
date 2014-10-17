package myscores.mappers;

import myscores.constants.Key;
import myscores.domain.AuthUser;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthUserMapper extends NodeMapper<AuthUser> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUserMapper.class);

    @Override
    public AuthUser map(Node node) {
        if (node != null && hasLabel(node, createLabel())) {
            LOGGER.debug("Mapping node to domain object");
            AuthUser authUser = new AuthUser();
            authUser.setUsername(getStringProperty(node, Key.USERNAME));
            authUser.setPassword(getStringProperty(node, Key.PASSWORD));
            authUser.setSalt(getStringProperty(node, Key.SALT));
            return authUser;
        } else {
            LOGGER.debug("Node is null or has wrong label");
            return null;
        }
    }

    @Override
    public Node map(Node node, AuthUser authUser) {
        if (node != null && authUser != null) {
            LOGGER.debug("Mapping domain object to node");
            node.addLabel(createLabel());
            setProperty(node, Key.USERNAME, authUser.getUsername());
            setProperty(node, Key.PASSWORD, authUser.getPassword());
            setProperty(node, Key.SALT, authUser.getSalt());
            return node;
        } else {
            LOGGER.debug("Node or domain object is null");
            return null;
        }
    }

    @Override
    public Key getDefaultKey() {
        return Key.USERNAME;
    }

    @Override
    public Label createLabel() {
        return createLabel(AuthUser.class);
    }
}
