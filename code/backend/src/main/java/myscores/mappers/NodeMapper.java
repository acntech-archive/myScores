package myscores.mappers;

import myscores.database.Props;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import java.util.ArrayList;
import java.util.List;

public abstract class NodeMapper<T> extends Mapper<T, Node> {

    @Override
    public T map(Index<Node> index, int id) {
        Node node = index.get(Props.ID, id).getSingle();
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

    public IndexHits<Node> getAllNodes(Index<Node> index) {
        return index.query(Props.ID, Props.ALL);
    }

    public int getNodeCount(Index<Node> index) {
        return getAllNodes(index).size();
    }

    public Node getNodeById(Index<Node> index, int id) {
        return index.get(Props.ID, id).getSingle();
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

    public int getIdProperty(Node node) {
        return (Integer) node.getProperty(Props.ID);
    }

    public String getNameProperty(Node node) {
        return (String) node.getProperty(Props.NAME);
    }
}
