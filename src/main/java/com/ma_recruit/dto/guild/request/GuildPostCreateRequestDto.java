package com.ma_recruit.dto.guild.request;

import com.ma_recruit.entity.member.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;

@Getter
public class GuildPostCreateRequestDto {

    @NotBlank
    @Schema()
    private String description;

    private Job job;

    @NotNull
    private String guild_name;

    private ArrayList<String> condition;
}
