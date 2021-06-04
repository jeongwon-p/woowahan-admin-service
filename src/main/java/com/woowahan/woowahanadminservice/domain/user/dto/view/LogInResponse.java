package com.woowahan.woowahanadminservice.domain.user.dto.view;

public class LogInResponse {

    private final String name;

    private final String token;

    private final String emailId;

    public LogInResponse(String name, String token, String emailId) {
        this.name = name;
        this.token = token;
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getEmailId() {
        return emailId;
    }
}
