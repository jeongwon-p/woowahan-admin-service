package com.woowahan.woowahanadminservice;

import com.woowahan.woowahanadminservice.domain.user.dao.UserRepository;
import com.woowahan.woowahanadminservice.domain.user.dto.request.UserHideRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.view.UserView;
import com.woowahan.woowahanadminservice.domain.user.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userDao;

    public UserService(
            BCryptPasswordEncoder encoder,
            UserRepository userDao
    ) {
        this.encoder = encoder;
        this.userDao = userDao;
    }

    @Transactional
    public void hideOrCancelArticle(UserHideRequestBody request) {
        User user = userDao.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();
        userDao.save(user);
    }

    @Transactional
    public void join(UserJoinRequestBody request) {
        userDao.save(new User(
                request.getEmailId(),
                request.isHidden(),
                request.getName(),
                encoder.encode(request.getPassword()),
                0,
                0)
        );
    }

    public List<UserView> searchMembers() {
        return userDao.findAll().stream().map(UserView::new).collect(Collectors.toList());
    }
}
