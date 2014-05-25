package myscores.database;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
public class LocalGraphDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalGraphDatabase.class);

    private GraphDatabaseService graph;

    @PostConstruct
    private void init() {
        try {
            Path tempDir = Files.createTempDirectory(Props.DATABASE_NAME);
            graph = new GraphDatabaseFactory().newEmbeddedDatabase(tempDir.toString());
        } catch (IOException e) {
            LOGGER.error("Error while initializing database", e);
        }
    }

    @PreDestroy
    private void teardown() {
        graph.shutdown();
    }

    public Transaction startTransaction() {
        return graph.beginTx();
    }

    public Index<Node> getNodeIndex(String name) {
        return graph.index().forNodes(name);
    }

    public Node createNode() {
        return graph.createNode();
    }
}
