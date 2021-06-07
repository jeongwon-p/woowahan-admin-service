package com.woowahan.woowahanadminservice.domain.user.dto.view;

import com.woowahan.woowahanadminservice.domain.user.entity.User;
import com.woowahan.woowahanadminservice.domain.user.type.Role;

public class UserView {

    private final String emailId;

    private final String name;

    private final boolean hidden;

    private final int ranking;

    private final Role role;

    private final int score;

    public UserView(User user) {
        this.emailId = user.getId();
        this.name = user.getName();
        this.hidden = user.isHidden();
        this.ranking = user.getRank();
        this.role = user.getRole();
        this.score = user.getScore();
    }

    public String getEmailId() {
        return emailId;
    }

    public String getName() {
        return name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getRanking() {
        return ranking;
    }

    public Role getRole() {
        return role;
    }

    public int getScore() {
        return score;
    }
}
