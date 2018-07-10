package com.example.wydnn.userguide.domain;

public class BaseResp<T>{
    public boolean error;
    public T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "BaseResp{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
