package myscores.database;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
public class LocalGraphDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalGraphDatabase.class);

    private GraphDatabaseService database;

    @PostConstruct
    private void init() {
        try {
            String databasePath = setupDatabaseDirectory();
            LOGGER.info("Starting graph database in directory {}", databasePath);
            database = createDatabase(databasePath);
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

    private String setupDatabaseDirectory() {
        Path databaseDirectory = FileSystems.getDefault().getPath(Props.DATABASE_ROOT, Props.DATABASE_NAME);
        if (!Files.isDirectory(databaseDirectory)) {
            try {
                Files.createDirectory(databaseDirectory);
            } catch (IOException e) {
                throw new DatabaseException("Error while trying to create database directory", e);
            }
        }
        return databaseDirectory.toString();
    }

    private GraphDatabaseService createDatabase(String databasePath) {
        GraphDatabaseBuilder databaseBuilder = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(databasePath);
        databaseBuilder.setConfig(GraphDatabaseSettings.allow_store_upgrade, "true");
        return databaseBuilder.newGraphDatabase();
    }
}
