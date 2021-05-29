package com.woowahan.woowahanadminservice;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Service
public class KakaoTalkOauth2UserService extends DefaultOAuth2UserService {

    private final HttpSession httpSession;

    public KakaoTalkOauth2UserService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        httpSession.setAttribute("login_info", attributes);
        return new DefaultOAuth2User( Collections.singleton(new SimpleGrantedAuthority("User")), attributes, "id" );
    }
}
