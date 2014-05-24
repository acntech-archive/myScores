package myscores.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Repository<T> {

    protected final Map<String, T> REPO = new HashMap<>();

    public T read(String id) {
        return REPO.get(id);
    }

    public List<T> find() {
        return new ArrayList<>(REPO.values());
    }

    public synchronized T create(String id, T data) {
        if (!REPO.containsKey(id)) {
            return REPO.put(id, data);
        } else {
            return null;
        }
    }

    public synchronized T update(String id, T data) {
        if (REPO.containsKey(id)) {
            return REPO.put(id, data);
        } else {
            return null;
        }
    }

    public synchronized T delete(String id) {
        return REPO.remove(id);
    }
}
