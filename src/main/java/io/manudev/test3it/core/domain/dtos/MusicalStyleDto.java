package io.manudev.test3it.core.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.manudev.test3it.core.domain.config.MusicalStyleDtoSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonSerialize(using = MusicalStyleDtoSerialize.class)
public class MusicalStyleDto implements Serializable {

    @JsonProperty("musicalStyleId")
    private String musicalStyleId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;



    @Builder
    public MusicalStyleDto(String musicalStyleId, String code, String name, String description) {
        this.musicalStyleId = musicalStyleId;
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
