package myscores.repositories;

import java.util.List;

public interface Repository<T> {

    T read(Object id);

    List<T> find();

    void create(T data);

    void update(T data);

    void delete(Object id);

    void add(Object parentId, Object childId);

    void remove(Object parentId, Object childId);

    int nextId();
}
