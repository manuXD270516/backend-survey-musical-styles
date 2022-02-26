package io.reflectoring.buckpal.account.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.reflectoring.buckpal.account.domain.config.PhoneDtoSerialize;
import io.reflectoring.buckpal.account.domain.config.UserDtoSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

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
