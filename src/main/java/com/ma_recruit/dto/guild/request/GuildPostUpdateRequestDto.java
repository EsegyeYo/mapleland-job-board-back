package com.ma_recruit.dto.guild.request;

import com.ma_recruit.entity.member.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.ArrayList;

@Getter
public class GuildPostUpdateRequestDto {

    @Schema (type = "String", example = "길드원 구해요")
    private JsonNullable<String> description = JsonNullable.undefined();

    @Schema (type = "String", example = "길드명")
    private JsonNullable<String> guild_name = JsonNullable.undefined();

    @Schema (type = "Enum", example = "프리스트")
    private JsonNullable<Job> job = JsonNullable.undefined();

    @Schema (type = "ArrayList<String>", example = "조건1, 조건2, 등등")
    private JsonNullable<ArrayList<String>> condition = JsonNullable.undefined();

}
