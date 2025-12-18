package com.ma_recruit.dto.member.request;

import com.ma_recruit.entity.member.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
public class ProfileUpdateRequestDto {

    @Schema (type = "String", example = "쫑이전사")
    private JsonNullable<String> nickname = JsonNullable.undefined();

    @Min(1)
    @Schema (type = "Integer", example = "200")
    private JsonNullable<Integer> level = JsonNullable.undefined();

    private JsonNullable<Job> job = JsonNullable.undefined();
}
