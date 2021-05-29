package com.woowahan.woowahanadminservice.common;

import com.woowahan.woowahanadminservice.KakaoTalkOauth2UserService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final KakaoTalkOauth2UserService kakaoOAuth2UserService;

    public SecurityConfig(KakaoTalkOauth2UserService kakaoOAuth2UserService) {
        this.kakaoOAuth2UserService = kakaoOAuth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login().userInfoEndpoint().userService(kakaoOAuth2UserService);
    }

}
