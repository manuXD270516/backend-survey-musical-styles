package io.manudev.test3it.core.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.manudev.test3it.core.domain.config.UserDtoSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonSerialize(using = UserDtoSerialize.class)
public class UserDto implements Serializable {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phones")
    private List<PhoneDto> phones;

    @Builder
    public UserDto(String userId, String fullname, String email, String password, List<PhoneDto> phones) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }
}
