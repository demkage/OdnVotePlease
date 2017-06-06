package com.rusifer.odnvtepls.domain;

import java.io.Serializable;

/**
 * Created by abosii on 6/6/2017.
 */
public class Poll implements Serializable {
    private String email;
    private int language;

    public Poll() {
    }

    public Poll(String email, int number) {
        this.email = email;
        this.language = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int number) {
        this.language = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Poll poll = (Poll) o;

        if (getLanguage() != poll.getLanguage()) return false;
        return getEmail() != null ? getEmail().equals(poll.getEmail()) : poll.getEmail() == null;

    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + getLanguage();
        return result;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "email='" + email + '\'' +
                ", language=" + language +
                '}';
    }
}
