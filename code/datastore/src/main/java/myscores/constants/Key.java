package myscores.constants;

public enum Key {
    ID("id"),
    NAME("name"),
    USERNAME("username"),
    FIRST_NAME("first-name"),
    MIDDLE_NAME("middle-name"),
    LAST_NAME("last-name"),
    ACTIVE("active"),
    SINCE("since"),
    RANK("rank"),
    PASSWORD("password"),
    SALT("salt");

    private String name;

    private Key(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
