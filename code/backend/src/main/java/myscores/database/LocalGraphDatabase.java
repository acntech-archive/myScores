package myscores.database;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

@Singleton
public class LocalGraphDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalGraphDatabase.class);

    private GraphDatabaseService database;

    @PostConstruct
    private void init() {
        try {
            String databasePath = DatabaseUtil.setupDatabaseDirectory();
            LOGGER.info("Starting graph database in directory {}", databasePath);
            database = DatabaseUtil.createDatabase(databasePath);
        } catch (DatabaseException e) {
            LOGGER.error("Error while initializing database", e);
        }
    }

    @PreDestroy
    private void teardown() {
        if (database != null) {
            database.shutdown();
        }
    }

    public Transaction startTransaction() {
        return database.beginTx();
    }

    public Index<Node> getNodeIndex(String name) {
        return database.index().forNodes(name);
    }

    public Node createNode() {
        return database.createNode();
    }
}
