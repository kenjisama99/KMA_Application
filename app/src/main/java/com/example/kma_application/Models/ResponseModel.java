package com.example.kma_application.Models;

public class ResponseModel {
    private String response;
    private String role;
    private Boolean res;

    public ResponseModel(String response, Boolean res) {
        this.response = response;
        this.res = res;
    }

    public ResponseModel(String response, String role, Boolean res) {
        this.response = response;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
