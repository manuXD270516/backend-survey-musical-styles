package io.manudev.test3it.core.domain.dtos.responses;

import io.manudev.test3it.core.adapter.out.persistence.user.UserJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ResponseCreateMusicStyleUserDto {

    private String userId;
    private List<ResponseMusicalStyleDto> musicalStyles;

    public static ResponseCreateMusicStyleUserDto mapEntityToDto(UserJpaEntity entity) {
        return ResponseCreateMusicStyleUserDto.builder()
                .userId(entity.getId())
                .musicalStyles(ResponseMusicalStyleDto.mapEntitiesToDtoList(new ArrayList<>(entity.getMusicalStyles())))
                .build();
    }


}
