package io.manudev.test3it.core.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.manudev.test3it.core.domain.config.MusicalStyleMetricDtoSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonSerialize(using = MusicalStyleMetricDtoSerialize.class)
public class MusicalStyleMetricDto implements Serializable {

    @JsonProperty("musicalStyleId")
    private String musicalStyleId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("totalLikes")
    private int totalLikes;


    @Builder
    public MusicalStyleMetricDto(String musicalStyleId, String code, String name, int totalLikes) {
        this.musicalStyleId = musicalStyleId;
        this.code = code;
        this.name = name;
        this.totalLikes = totalLikes;
    }
}
