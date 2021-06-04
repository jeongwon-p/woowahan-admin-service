package com.woowahan.woowahanadminservice.domain.user.dto.view;

public class TokenResponse {
    private final String accessToken;
    private final String tokenType;

    public TokenResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
