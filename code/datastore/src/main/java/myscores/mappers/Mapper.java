package myscores.mappers;

import myscores.constants.Key;
import myscores.constants.KeyValue;
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

    public abstract Key getDefaultKey();

    public boolean exists(Index<U> index, Object value) {
        return index.get(getDefaultKey().getName(), value).getSingle() != null;
    }

    public void safeSetProperty(U item, String key, Object value) {
        if (item != null && key != null && value != null) {
            item.setProperty(key, value);
        }
    }

    public Label createLabel(Class<T> clazz) {
        return DynamicLabel.label(clazz.getSimpleName().toUpperCase());
    }

    public int getIntProperty(U item, Key key) {
        return (Integer) item.getProperty(key.getName());
    }

    public String getStringProperty(U item, Key key) {
        return (String) item.getProperty(key.getName());
    }

    public boolean getBooleanProperty(U item, Key key) {
        return (Boolean) item.getProperty(key.getName());
    }

    public String safeGetStringProperty(U item, Key key) {
        if (item.hasProperty(key.getName())) {
            return getStringProperty(item, key);
        } else {
            return null;
        }
    }

    public static KeyValue keyValue(Key key, Object value) {
        return new KeyValue(key, value);
    }
}
