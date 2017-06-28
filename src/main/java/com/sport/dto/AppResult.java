package com.sport.dto;

/**
 * Created by EKO-LKB on 2017/1/14.
 */

//所有的ajax请求返回类型，封装json结果
public class AppResult<T> {
    private int status;
    private T data;
    private String error;

    public AppResult(int status){
        this.status = status;
    }

    public AppResult(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public AppResult(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
