package io.manudev.test3it.core.application.port.in.musicalStyle;

import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;
import io.manudev.test3it.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateMusicalStyleCommand extends SelfValidating<CreateUserMusicStyleCommand> {

    @NotNull
    private String code;

    @NotNull
    private String name;

    @Length(max = 200)
    //@Email
    private String description;

    public CreateMusicalStyleCommand(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.validateSelf();
    }
}
