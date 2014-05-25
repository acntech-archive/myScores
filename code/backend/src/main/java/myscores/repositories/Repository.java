package myscores.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Repository<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected abstract Map<String, T> getRepo();

    public T read(String id) {
        LOGGER.info("Read data for id {}", id);
        return getRepo().get(id);
    }

    public List<T> find() {
        LOGGER.info("Find data");
        return new ArrayList<>(getRepo().values());
    }

    public synchronized void create(String id, T data) {
        if (!getRepo().containsKey(id)) {
            LOGGER.info("Create data with id {}", id);
            getRepo().put(id, data);
        } else {
            LOGGER.warn("Create data failed. Data with id {} already exists", id);
        }
    }

    public synchronized void update(String id, T data) {
        if (getRepo().containsKey(id)) {
            LOGGER.info("Update data with id {}", id);
            getRepo().put(id, data);
        } else {
            LOGGER.warn("Update data failed. Data with id {} not found", id);
        }
    }

    public synchronized void delete(String id) {
        LOGGER.info("Delete data with id {}", id);
        getRepo().remove(id);
    }
}
