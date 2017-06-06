package com.rusifer.odnvtepls.domain;

import java.util.List;

/**
 * Created by abosii on 6/6/2017.
 */
public class Statistic {
    private List<Response> history;
    private int count;
    private int success;
    private int errors;

    public List<Response> getHistory() {
        return history;
    }

    public void setHistory(List<Response> history) {
        this.history = history;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public void success() {
        ++success;
    }

    public void error() {
        ++errors;
    }

    public void count() {
        ++count;
    }
}
