package com.adobe.interview.blog.components.login;

public class LoginResponseDTO {

    private boolean statusOk;
    private String errorMessage;

    public LoginResponseDTO(boolean statusOk, String errorMessage) {
        this.statusOk = statusOk;
        this.errorMessage = errorMessage;
    }

    public boolean isStatusOk() {
        return statusOk;
    }

    public void setStatusOk(boolean statusOk) {
        this.statusOk = statusOk;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
