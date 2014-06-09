package myscores.repositories;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public abstract class Repository<T> {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public abstract T read(int id);

    public abstract List<T> find();

    public abstract void create(T data);

    public abstract void update(T data);

    public abstract void delete(int id);

    protected abstract int nextId();

    protected String getCurrentTime() {
        return getTime(DateTime.now());
    }

    protected String getTime(DateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN);
        return formatter.print(dateTime);
    }

    protected DateTime getTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN);
        return formatter.parseDateTime(dateTime);
    }
}
