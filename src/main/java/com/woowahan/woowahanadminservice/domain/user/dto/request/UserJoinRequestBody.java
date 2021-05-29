package com.woowahan.woowahanadminservice.domain.user.dto.request;

public class UserJoinRequestBody {

    private String emailId;

    private boolean hidden;

    private String name;

    private String password;

    public String getEmailId() {
        return emailId;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
