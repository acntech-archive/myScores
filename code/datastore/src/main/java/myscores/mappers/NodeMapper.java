package myscores.mappers;

import myscores.constants.Key;
import myscores.constants.Props;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import java.util.ArrayList;
import java.util.List;

public abstract class NodeMapper<T> extends Mapper<T, Node> {

    @Override
    public T map(Index<Node> index, Object value) {
        Node node = getNode(index, value);
        return map(node);
    }

    @Override
    public List<T> mapAll(Index<Node> index) {
        List<T> items = new ArrayList<>();
        IndexHits<Node> nodes = getAllNodes(index);
        if (nodes != null) {
            for (Node node : nodes) {
                T item = map(node);
                if (item != null) {
                    items.add(item);
                }
            }
        }
        return items;
    }

    protected void setProperty(Node node, Key key, Object value) {
        node.setProperty(key.getName(), value);
    }

    public IndexHits<Node> getAllNodes(Index<Node> index) {
        return index.query(getDefaultKey().getName(), Props.ALL);
    }

    public int getNodeCount(Index<Node> index) {
        return getAllNodes(index).size();
    }

    public Node getNode(Index<Node> index, Object value) {
        return index.get(getDefaultKey().getName(), value).getSingle();
    }

    public abstract Label createLabel();

    public boolean hasLabel(Node node, Label label) {
        if (node != null && label != null) {
            for (Label nodeLabel : node.getLabels()) {
                if (nodeLabel != null && label.name().equals(nodeLabel.name())) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}
