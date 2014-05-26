package myscores.mappers;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

import java.util.List;

public abstract class Mapper<T> {

    public abstract T mapNode(Index<Node> index, int id);

    public abstract List<T> mapAllNodes(Index<Node> index);

    public abstract T mapNode(Node node);

    public abstract Node mapNode(Node node, T data);
}
