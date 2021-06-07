package com.woowahan.woowahanadminservice;

import com.woowahan.woowahanadminservice.common.AuthorityException;
import com.woowahan.woowahanadminservice.common.InvalidJwtTokenException;
import com.woowahan.woowahanadminservice.domain.user.dao.UserRepository;
import com.woowahan.woowahanadminservice.domain.user.dao.UserSessionTokenRepository;
import com.woowahan.woowahanadminservice.domain.user.dto.request.LogInRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.request.UserHideRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.view.LogInResponse;
import com.woowahan.woowahanadminservice.domain.user.dto.view.UserView;
import com.woowahan.woowahanadminservice.domain.user.entity.User;
import com.woowahan.woowahanadminservice.domain.user.entity.UserSessionToken;
import com.woowahan.woowahanadminservice.domain.user.type.Role;
import com.woowahan.woowahanadminservice.util.JwtTokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userDao;
    private final UserSessionTokenRepository userSessionTokenDao;

    public UserService(
            BCryptPasswordEncoder encoder,
            JwtTokenProvider jwtTokenProvider,
            UserRepository userDao,
            UserSessionTokenRepository userSessionTokenDao
    ) {
        this.encoder = encoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDao = userDao;
        this.userSessionTokenDao = userSessionTokenDao;
    }

    @Transactional
    public void hideOrCancelUser(UserHideRequestBody request) {
        User targetUser = userDao.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new)
                .hideOrCancel();

        userDao.save(targetUser);
    }

    @Transactional
    public void join(UserJoinRequestBody request) {
        Pattern pattern = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
        Matcher matcher = pattern.matcher(request.getEmailId());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email format is not correct");
        }

        userDao.save(new User(
                request.getEmailId(),
                request.isHidden(),
                request.getName(),
                encoder.encode(request.getPassword()),
                0,
                Role.USER,
                0)
        );
    }

    @Transactional
    public void modifyUser(UserJoinRequestBody request) {
        User user = userDao.findById(request.getEmailId())
                .orElseThrow(EntityNotFoundException::new);

        userDao.save(new User(
                user.getId(),
                user.isHidden(),
                request.getName(),
                encoder.encode(request.getPassword()),
                user.getRank(),
                user.getRole(),
                user.getScore())
        );
    }

    @Transactional
    public LogInResponse logIn(LogInRequestBody request) {
        User user = userDao.findById(request.getEmailId())
                .filter(info -> encoder.matches(request.getPassword(), info.getPassword())).stream()
                .findAny()
                .orElseThrow(() -> new IllegalAccessError("Email or password is wrong"));

        return new LogInResponse(
                user.getName(),
                this.createToken(user, List.of(user.getRole())),
                user.getId()
        );
    }

    private String createToken(User user, List<Role> roles) {
        String accessToken = jwtTokenProvider.createToken(user.getId(), roles);
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), roles, accessToken);
        UserSessionToken userSessionToken = new UserSessionToken(user.getId(), refreshToken);
        userSessionTokenDao.save(userSessionToken);

        return accessToken;
    }

    @Transactional
    public LogInResponse refreshUserTokens(String userId, String summitToken) {
        UserSessionToken userSessionToken = userSessionTokenDao.findById(userId).orElseThrow(EntityNotFoundException::new);
        String currentAccessToken = jwtTokenProvider.getClaimsFromJwtToken(userSessionToken.getToken()).toString();

        if (currentAccessToken.equals(summitToken)) {
            User user = userDao.findById(userId).orElseThrow(EntityNotFoundException::new);

            return new LogInResponse(
                    user.getName(),
                    this.createToken(user, List.of(user.getRole())),
                    user.getId()
            );
        }else{
            throw new InvalidJwtTokenException("There is no valid token");
        }
    }

    public UserView searchUser(String userId) {
        return new UserView(userDao.findById(userId).orElseThrow(EntityNotFoundException::new));
    }

    public List<UserView> searchUsers() {
        return userDao.findAllByHiddenFalse().stream().map(UserView::new).collect(Collectors.toList());
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }
}
