package myscores.database;

import myscores.env.EnvException;
import myscores.env.EnvUtil;
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

@Singleton
public class LocalGraphDatabase implements GraphDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalGraphDatabase.class);

    private GraphDatabaseService database;

    @PostConstruct
    private void init() {
        try {
            String databasePath = EnvUtil.getDbDir();
            LOGGER.info("Starting graph database in directory {}", databasePath);
            database = createDatabase(databasePath);
        } catch (EnvException | DatabaseException e) {
            LOGGER.error("Error while initializing database", e);
        }
    }

    @PreDestroy
    private void teardown() {
        if (database != null) {
            database.shutdown();
        }
    }

    @Override
    public Transaction startTransaction() {
        return database.beginTx();
    }

    @Override
    public Index<Node> getNodeIndex(String name) {
        return database.index().forNodes(name);
    }

    @Override
    public Node createNode() {
        return database.createNode();
    }

    private GraphDatabaseService createDatabase(String databasePath) {
        GraphDatabaseBuilder databaseBuilder = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(databasePath);
        databaseBuilder.setConfig(GraphDatabaseSettings.allow_store_upgrade, "true");
        return databaseBuilder.newGraphDatabase();
    }
}
