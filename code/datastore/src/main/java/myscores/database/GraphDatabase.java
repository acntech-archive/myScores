package myscores.database;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

public interface GraphDatabase {

    Transaction startTransaction();

    Index<Node> getNodeIndex(String name);

    Node createNode();
}
