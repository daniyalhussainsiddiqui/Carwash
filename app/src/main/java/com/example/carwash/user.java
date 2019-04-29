package com.example.carwash;

public class user { String email;
    String name;
    String uid;

    public user(String email, String name, String uid) {
        this.email = email;
        this.name = name;
        this.uid = uid;
    }

    public user() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

