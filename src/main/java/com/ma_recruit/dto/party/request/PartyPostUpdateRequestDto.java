package com.ma_recruit.dto.party.request;

import com.ma_recruit.entity.member.Job;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigInteger;

@Getter
public class PartyPostUpdateRequestDto {
    // TODO: Job이 구한 직업으로 쓸 건지 구하는 직업으로 쓰는지 확인 (25/12/07)
    @Schema (type = "string", example = "설명")
    private JsonNullable<String> description = JsonNullable.undefined();;

    private JsonNullable<Job> job = JsonNullable.undefined();;

    @Schema (type = "BigInteger", example = "1")
    private JsonNullable<BigInteger> mapId = JsonNullable.undefined();;

    @Min(1)
    @Schema (type = "Integer", example = "1")
    private JsonNullable<Integer> member_max_count = JsonNullable.undefined();;

    @Min(1)
    @Schema (type = "Integer", example = "1")
    private JsonNullable<Integer> member_count = JsonNullable.undefined();;
}
