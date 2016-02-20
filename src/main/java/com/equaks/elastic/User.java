package com.equaks.elastic;

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
}
