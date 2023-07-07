package com.springboot.georlock.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(

    @NotBlank(message = "출입가능 시작 시간이 없습니다.")
    String inTime,

    @NotBlank(message = "출입가능 종료 시간이 없습니다.")
    String outTime
) {

}
