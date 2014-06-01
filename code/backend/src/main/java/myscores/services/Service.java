package myscores.services;

import java.util.List;

public abstract class Service<T> {

    public abstract T get(int id);

    public abstract List<T> find();

    public abstract void register(T data);

    public abstract void change(T data);

    public abstract void delete(int id);
}
