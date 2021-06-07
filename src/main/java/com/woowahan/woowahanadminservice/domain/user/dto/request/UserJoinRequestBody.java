package com.woowahan.woowahanadminservice.domain.user.dto.request;

import com.woowahan.woowahanadminservice.domain.user.entity.User;
import com.woowahan.woowahanadminservice.domain.user.type.Role;

public class UserJoinRequestBody {

    private String emailId;

    private boolean hidden;

    private String name;

    private String password;

    public User toUser() {
        return new User(
                this.emailId,
                this.hidden,
                this.name,
                this.password,
                0,
                Role.USER,
                0
        );
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
