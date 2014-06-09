package myscores.mappers;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.index.Index;

import java.util.List;

public abstract class Mapper<T, U extends PropertyContainer> {

    public abstract T map(Index<U> index, Object value);

    public abstract List<T> mapAll(Index<U> index);

    public abstract T map(U item);

    public abstract Node map(U item, T data);

    public abstract String getDefaultKey();

    public boolean exists(Index<U> index, Object value) {
        return index.get(getDefaultKey(), value).getSingle() != null;
    }

    public void safeSetProperty(U item, String key, Object value) {
        if (item != null && key != null && value != null) {
            item.setProperty(key, value);
        }
    }

    public Label createLabel(Class<T> clazz) {
        return DynamicLabel.label(clazz.getSimpleName().toUpperCase());
    }

    public int getIntProperty(U item, String key) {
        return (Integer) item.getProperty(key);
    }

    public String getStringProperty(U item, String key) {
        return (String) item.getProperty(key);
    }

    public boolean getBooleanProperty(U item, String key) {
        return (Boolean) item.getProperty(key);
    }
}
