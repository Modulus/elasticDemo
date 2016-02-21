package com.eguaks.elastic;

import java.time.LocalDate;

/**
 * Created by Modulus on 20.02.2016.
 */
public class User {
    private String id;
    private String userName;
    private LocalDate created;
    private LocalDate lastLogIn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getLastLogIn() {
        return lastLogIn;
    }

    public void setLastLogIn(LocalDate lastLogIn) {
        this.lastLogIn = lastLogIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (created != null ? !created.equals(user.created) : user.created != null) return false;
        return lastLogIn != null ? lastLogIn.equals(user.lastLogIn) : user.lastLogIn == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastLogIn != null ? lastLogIn.hashCode() : 0);
        return result;
    }
}
