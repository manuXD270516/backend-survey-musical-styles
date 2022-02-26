package io.reflectoring.buckpal.account.domain.dtos.responses;


import io.reflectoring.buckpal.account.adapter.out.persistence.user.UserJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ResponseCreateUserDto {

    private String id;
    private Date created;
    private Date modified;
    private Date last_login;
    private String token;
    private boolean isActive;

    public static ResponseCreateUserDto mapEntityToDto(UserJpaEntity entity){
        return ResponseCreateUserDto.builder()
                .id(entity.getId())
                .created(entity.getCreatedAt())
                .modified(entity.getLastModifiedAt())
                .last_login(entity.getLastLogin())
                .token(entity.getAccessToken().toString())
                .isActive(entity.getStatus() == 1)
                .build();
    }


}
