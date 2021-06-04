package com.woowahan.woowahanadminservice.domain.user.dao;

import com.woowahan.woowahanadminservice.domain.user.entity.UserSessionToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionTokenRepository extends JpaRepository<UserSessionToken, String> {
}
