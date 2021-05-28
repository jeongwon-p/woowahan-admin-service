package com.woowahan.woowahanadminservice.controller;

import com.woowahan.woowahanadminservice.domain.user.util.ExcelGenerator;
import com.woowahan.woowahanadminservice.UserService;
import com.woowahan.woowahanadminservice.domain.user.dto.request.UserHideRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.request.UserJoinRequestBody;
import com.woowahan.woowahanadminservice.domain.user.dto.view.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("사용자 숨기기 및 숨기기 취소")
    @PostMapping(value = "/user/hide")
    public void hideOrCancelArticle(@RequestBody UserHideRequestBody request) {
        userService.hideOrCancelArticle(request);
    }

    @ApiOperation("사용자 등록")
    @PostMapping(value = "/user/join")
    public void join(@RequestBody UserJoinRequestBody request) {
        userService.join(request);
    }

    @ApiOperation("사용자 목록 조회")
    @GetMapping(value = "/user")
    public ResponseEntity<List<UserView>> searchMember() {
        return ResponseEntity.ok(userService.searchUsers());
    }

    @ApiOperation("사용자 목록 Excel 다운로드")
    @GetMapping(value = "/user/export/excel")
    public ResponseEntity<InputStreamResource> exportUserToExcel(@RequestParam List<String> emailId) throws IOException {
        ByteArrayInputStream in = ExcelGenerator.exportUserToExcel(userService.getUsers(emailId));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=user.xlsx");

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
