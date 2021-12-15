package com.koreait.basic.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResult {
    private final int result;
    private final UserEntity loginUser;
}
