package io.reflectoring.buckpal.account.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.reflectoring.buckpal.account.domain.config.UserDtoSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonSerialize(using = UserDtoSerialize.class)
public class UserDto implements Serializable {

    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phones")
    private List<PhoneDto> phones;

    public UserDto(){}

    @Builder
    public UserDto(String fullname, String email, String password, List<PhoneDto> phones) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }
}
