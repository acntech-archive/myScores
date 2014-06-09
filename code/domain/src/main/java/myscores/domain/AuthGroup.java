package myscores.domain;

import java.util.List;

public class AuthGroup {

    private String name;
    private List<AuthUser> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthUser> getUsers() {
        return users;
    }

    public void setUsers(List<AuthUser> users) {
        this.users = users;
    }
}
