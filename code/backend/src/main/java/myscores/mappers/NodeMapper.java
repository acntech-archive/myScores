package myscores.mappers;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NodeMapper<T> extends Mapper<T, Node> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public boolean hasLabel(Node node, Label label) {
        if (node != null && label != null) {
            for (Label nodeLabel : node.getLabels()) {
                LOGGER.debug("Comparing {} to {}", label.name(), nodeLabel.name());
                if (nodeLabel != null && label.name().equals(nodeLabel.name())) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}
