package com.rusifer.odnvtepls.domain;

import java.io.Serializable;

/**
 * Created by abosii on 6/6/2017.
 */
public class Error  implements Serializable{
    private String param;
    private String msg;
    private String value;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Error error = (Error) o;

        if (getParam() != null ? !getParam().equals(error.getParam()) : error.getParam() != null) return false;
        if (getMsg() != null ? !getMsg().equals(error.getMsg()) : error.getMsg() != null) return false;
        return getValue() != null ? getValue().equals(error.getValue()) : error.getValue() == null;

    }

    @Override
    public int hashCode() {
        int result = getParam() != null ? getParam().hashCode() : 0;
        result = 31 * result + (getMsg() != null ? getMsg().hashCode() : 0);
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Error{" +
                "param='" + param + '\'' +
                ", msg='" + msg + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
