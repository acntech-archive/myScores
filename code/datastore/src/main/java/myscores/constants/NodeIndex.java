package myscores.constants;

public enum NodeIndex {
    AUTH_GROUPS("auth-groups"),
    AUTH_USERS("auth-users"),
    PARTIES("parties"),
    GAMBLERS("gamblers"),
    MATCHES("matches"),
    TEAMS("teams");

    private String name;

    private NodeIndex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
