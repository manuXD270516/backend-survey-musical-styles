package io.manudev.test3it.core.application.port.out.user;

import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.adapter.out.persistence.user.UserJpaEntity;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;

import java.util.List;

public interface UserPort {

    PersistenceResponse createUser(CreateUserCommand command);
    PersistenceResponse registerMusicStyleOfUser(CreateUserMusicStyleCommand command);
    UserJpaEntity findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
