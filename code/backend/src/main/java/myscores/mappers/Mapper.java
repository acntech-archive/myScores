package myscores.mappers;

import myscores.database.Props;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.index.Index;

import java.util.List;

public abstract class Mapper<T, S extends PropertyContainer> {

    public abstract T map(Index<S> index, int id);

    public abstract List<T> mapAll(Index<S> index);

    public abstract T map(S item);

    public abstract Node map(S item, T data);

    public boolean exists(Index<S> index, int id) {
        return index.get(Props.ID, id).getSingle() != null;
    }

    public Label createLabel(Class<T> clazz) {
        return DynamicLabel.label(clazz.getSimpleName().toUpperCase());
    }

    public int getIntProperty(S item, String key) {
        return (Integer) item.getProperty(key);
    }

    public String getStringProperty(S item, String key) {
        return (String) item.getProperty(key);
    }

    public boolean getBooleanProperty(S item, String key) {
        return (Boolean) item.getProperty(key);
    }
}
