package myscores.database;

import org.neo4j.graphdb.Label;

public abstract class NodeLabel implements Label {

    @Override
    public abstract String name();
}
