package com.go.jpastudy.config.auth.dto;

import com.go.jpastudy.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * SessionUser -> 인증된 사용자 정보만 필요
 * Serializable -> 우리가 만든 클래스가 파일에 읽거나 쓸 수 있도록 하거나 다른 서버로 보내거나 받을 수 있도록 함.
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email  = user.getEmail();
        this.picture = user.getPicture();
    }
}
