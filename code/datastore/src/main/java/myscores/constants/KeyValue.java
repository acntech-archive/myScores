package myscores.constants;

public class KeyValue {

    private Key key;
    private Object value;

    public KeyValue() {
    }

    public KeyValue(Key key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
