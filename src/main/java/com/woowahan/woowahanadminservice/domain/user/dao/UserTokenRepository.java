package com.woowahan.woowahanadminservice.domain.user.dao;

import com.woowahan.woowahanadminservice.domain.user.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, String> {
}
