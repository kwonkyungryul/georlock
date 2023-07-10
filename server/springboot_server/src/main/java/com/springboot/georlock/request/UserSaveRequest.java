package com.springboot.georlock.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record UserSaveRequest (

        @NotBlank(message = "사번은 필수 입력 값입니다.")
        String empNo,

        @NotBlank(message = "이름은 필수 입력 값입니다.")
        String username
) {
}
