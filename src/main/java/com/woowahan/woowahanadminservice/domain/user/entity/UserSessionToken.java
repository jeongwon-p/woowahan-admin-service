package com.woowahan.woowahanadminservice.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(
        name = "usertoken"
)
@Entity
public class UserSessionToken {

    @Id
    @Column(name = "email_id")
    private String userId;

    @Column(name = "token")
    private String token;

    public UserSessionToken() {
    }

    public UserSessionToken(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSessionToken userSessionToken = (UserSessionToken) o;
        return userId.equals(userSessionToken.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
