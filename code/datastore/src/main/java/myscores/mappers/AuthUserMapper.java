package myscores.mappers;

import myscores.constants.Props;
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
            authUser.setUsername(getStringProperty(node, Props.USERNAME));
            authUser.setPassword(getStringProperty(node, Props.PASSWORD));
            authUser.setSalt(getStringProperty(node, Props.SALT));
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
            node.setProperty(Props.USERNAME, authUser.getUsername());
            node.setProperty(Props.PASSWORD, authUser.getPassword());
            node.setProperty(Props.SALT, authUser.getSalt());
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
        return createLabel(AuthUser.class);
    }
}
