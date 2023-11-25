package com.dashboard.gmelan.user.config;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserCreationForm {
    @Size(min=3, max=15, message = "ID는 3자에서 15자 사이여야 합니다.")
    @NotEmpty(message = "ID는 필수 항목입니다.")
    private String username;

    @Size(min=8, max=255, message = "비밀번호는 8자 이상이어야 합니다.")
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    private String passwordValidation;

    @Email
    @NotEmpty(message = "이메일은 필수 항목입니다.")
    private String email;

}
