package io.manudev.test3it.core.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.manudev.test3it.core.domain.config.PhoneDtoSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize(using = PhoneDtoSerialize.class)
public class PhoneDto {

    @JsonProperty("number")
    private String number;

    @JsonProperty("cityCode")
    private String cityCode;

    @JsonProperty("contryCode")
    private String contryCode;
}
