package myscores.relationships;

import org.neo4j.graphdb.RelationshipType;

public enum ForGambler implements RelationshipType {
    BELONGS_TO, PREDICTS_RESULT
}
