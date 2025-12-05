package com.ma_recruit.dto.member.request;

import com.ma_recruit.entity.member.Job;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProfileCreateRequestDto {

    @NotBlank
    private String nickname;

    @Min(1)
    private int level;

    @NotNull
    private Job job;
}
