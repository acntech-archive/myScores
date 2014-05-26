package myscores.relationships;

import org.neo4j.graphdb.RelationshipType;

public enum Basic implements RelationshipType {
    BELONGS_TO, PREDICTS_RESULT
}
