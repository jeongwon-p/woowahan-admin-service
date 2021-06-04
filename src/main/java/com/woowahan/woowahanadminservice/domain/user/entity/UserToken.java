package com.woowahan.woowahanadminservice.domain.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(
        name = "usertoken"
)
@Entity
public class UserToken {

    @Id
    private String userId;

    private String token;

    public UserToken() {
    }

    public UserToken(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToken userToken = (UserToken) o;
        return userId.equals(userToken.userId);
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
