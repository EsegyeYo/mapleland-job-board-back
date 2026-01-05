package com.ma_recruit.dto.raid.request;

import com.ma_recruit.entity.member.Job;
import com.ma_recruit.entity.party.PartyType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigInteger;
@Getter
public class RaidPostCreateRequestDto {

    @NotBlank
    private String description;

    @NotNull
    private PartyType partyType;

    @NotNull
    private Job job;

    @NotNull
    private int mobId;

    @Min(1)
    private int member_max_count;

    @Min(1)
    private int member_count;
}
