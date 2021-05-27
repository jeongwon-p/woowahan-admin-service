package com.woowahan.woowahanadminservice.controller;

import com.woowahan.woowahanadminservice.UserService;
import com.woowahan.woowahanadminservice.domain.user.dto.request.UserHideRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.view.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("사용자 숨기기 및 숨기기 취소")
    @PostMapping(value = "/hide")
    public void hideOrCancelArticle(@RequestBody UserHideRequestBody request) {
        userService.hideOrCancelArticle(request);
    }

    @ApiOperation("사용자 등록")
    @PostMapping(value = "/join")
    public void join(@RequestBody UserJoinRequestBody request) {
        userService.join(request);
    }

    @ApiOperation("사용자 목록 조회")
    @GetMapping(value = "/member")
    public ResponseEntity<List<UserView>> searchMember() {
        return ResponseEntity.ok(userService.searchMembers());
    }

    // TODO: excel 다운로드
}
