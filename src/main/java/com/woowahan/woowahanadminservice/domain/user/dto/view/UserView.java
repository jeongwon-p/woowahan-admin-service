package com.woowahan.woowahanadminservice.domain.user.dto.view;

import com.woowahan.woowahanadminservice.domain.user.entity.User;

public class UserView {

    private String emailId;

    private String name;

    private boolean hidden;

    private int ranking;

    private int score;

    public UserView(User user) {
        this.emailId = user.getEmailId();
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