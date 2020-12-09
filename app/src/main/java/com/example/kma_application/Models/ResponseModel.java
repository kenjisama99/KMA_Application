package com.example.kma_application.Models;

public class ResponseModel {
    private String response;
    private Boolean res;

    public ResponseModel(String response, Boolean res) {
        this.response = response;
        this.res = res;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getRes() {
        return res;
    }

    public void setRes(Boolean res) {
        this.res = res;
    }
}
