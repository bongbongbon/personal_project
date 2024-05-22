package com.zerobase.project.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    @Size(min = 3, max = 25, message = "글자 사이즈는 3자 에서 25자 사이여야 합니다.")
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;

    @NotEmpty(message = "고객 혹은 파트너 중 하나를 필수로 선택해주세요.")
    private String userRole;

}
