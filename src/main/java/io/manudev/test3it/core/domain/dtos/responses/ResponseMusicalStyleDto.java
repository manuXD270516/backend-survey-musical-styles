package io.manudev.test3it.core.domain.dtos.responses;

import io.manudev.test3it.core.adapter.out.persistence.musicalstyle.MusicalStyleJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ResponseMusicalStyleDto {

    private String musicalStyleId;
    private String name;
    private String description;

    public static ResponseMusicalStyleDto mapEntityToDto(MusicalStyleJpaEntity entity) {
        return ResponseMusicalStyleDto.builder()
                .musicalStyleId(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public static List<ResponseMusicalStyleDto> mapEntitiesToDtoList(List<MusicalStyleJpaEntity> entities) {
        return entities.stream()
                .map(entity -> mapEntityToDto(entity))
                .collect(Collectors.toList());
    }


}
