package myscores.repositories;

import java.util.List;

public abstract class Repository<T> {

    private int id = 0;

    public int getNextId() {
        return ++id;
    }

    public abstract T read(int id);

    public abstract List<T> find();

    public abstract void create(T data);

    public abstract void update(T data);

    public abstract void delete(int id);
}
