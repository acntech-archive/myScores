package myscores.database;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public final class DatabaseUtil {

    private DatabaseUtil() {
    }

    public static String setupDatabaseDirectory() {
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

    public static GraphDatabaseService createDatabase(String databasePath) {
        GraphDatabaseBuilder databaseBuilder = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(databasePath);
        databaseBuilder.setConfig(GraphDatabaseSettings.allow_store_upgrade, "true");
        return databaseBuilder.newGraphDatabase();
    }
}
