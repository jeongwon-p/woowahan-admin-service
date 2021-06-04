package com.woowahan.woowahanadminservice.domain.user.dto.view;

import com.woowahan.woowahanadminservice.domain.user.entity.User;

public class UserView {

    private final String emailId;

    private final String name;

    private final boolean hidden;

    private final int ranking;

    private final int score;

    public UserView(User user) {
        this.emailId = user.getId();
        this.name = user.getName();
        this.hidden = user.isHidden();
        this.ranking = user.getRank();
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

    public int getScore() {
        return score;
    }
}
