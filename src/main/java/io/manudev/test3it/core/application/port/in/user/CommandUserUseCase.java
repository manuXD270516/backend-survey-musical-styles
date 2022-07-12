package io.manudev.test3it.core.application.port.in.user;

import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;

public interface CommandUserUseCase {

    PersistenceResponse create(CreateUserCommand createUserCommand);
    PersistenceResponse registerMusicStyleOfUser(CreateUserMusicStyleCommand createUserMusicStyleCommand);

}
