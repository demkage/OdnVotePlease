package com.rusifer.odnvtepls.domain;

import java.io.Serializable;

/**
 * Created by abosii on 6/6/2017.
 */
public class User implements Serializable{
    private String email;
    private String name;

    public User() {
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        return getName() != null ? getName().equals(user.getName()) : user.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
