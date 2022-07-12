package io.manudev.test3it.core.application.port.in.userMusicStyle;

import io.manudev.test3it.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateUserMusicStyleCommand extends SelfValidating<CreateUserMusicStyleCommand> {

    @NotNull
    private String userId;

    @NotNull
    private String musicalStyleId;


    public CreateUserMusicStyleCommand(String userId, String musicalStyleId) {
        this.userId = userId;
        this.musicalStyleId = musicalStyleId;
        this.validateSelf();
    }
}
