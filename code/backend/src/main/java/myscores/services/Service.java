package myscores.services;

import myscores.validation.Id;

import javax.validation.Valid;
import java.util.List;

public abstract class Service<T> {

    public abstract T get(@Id int id);

    public abstract List<T> find();

    public abstract void register(@Valid T data);

    public abstract void change(@Valid T data);

    public abstract void delete(@Id int id);
}
