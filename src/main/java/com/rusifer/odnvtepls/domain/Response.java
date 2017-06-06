package com.rusifer.odnvtepls.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by abosii on 6/6/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private String status;
    private List<Error> errors;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (getStatus() != null ? !getStatus().equals(response.getStatus()) : response.getStatus() != null)
            return false;
        return getErrors() != null ? getErrors().equals(response.getErrors()) : response.getErrors() == null;

    }

    @Override
    public int hashCode() {
        int result = getStatus() != null ? getStatus().hashCode() : 0;
        result = 31 * result + (getErrors() != null ? getErrors().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", errors=" + errors +
                '}';
    }
}
