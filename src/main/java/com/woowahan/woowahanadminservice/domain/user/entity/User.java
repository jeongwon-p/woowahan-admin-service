package com.woowahan.woowahanadminservice.domain.user.entity;

import com.woowahan.woowahanadminservice.common.BooleanToYnConverter;
import com.woowahan.woowahanadminservice.domain.user.type.Role;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(
        name = "user"
)
public class User {

    @Id
    @Column(name = "email_id")
    private String id;

    @Column(name = "hide_yn", columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanToYnConverter.class)
    private boolean hidden;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "ranking")
    private int rank;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "score")
    private int score;

    public User() {
    }

    public User(
            String id,
            boolean hidden,
            String name,
            String password,
            int rank,
            Role role,
            int score
    ) {
        this.id = id;
        this.hidden = hidden;
        this.name = name;
        this.password = password;
        this.rank = rank;
        this.role = role;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public User hideOrCancel() {
        return new User(
                this.id,
                !this.hidden,
                this.name,
                this.password,
                this.rank,
                this.role,
                this.score
        );
    }

    public String getId() {
        return id;
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

    public int getRank() {
        return rank;
    }

    public Role getRole() {
        return role;
    }

    public int getScore() {
        return score;
    }
}
