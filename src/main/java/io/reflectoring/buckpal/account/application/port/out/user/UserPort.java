package io.reflectoring.buckpal.account.application.port.out.user;

import io.reflectoring.buckpal.account.adapter.out.persistence.user.UserJpaEntity;
import io.reflectoring.buckpal.account.application.port.in.user.CreateUserCommand;
import io.reflectoring.buckpal.account.domain.dtos.UserDto;
import io.reflectoring.buckpal.common.utils.PersistenceResponse;

public interface UserPort {

    PersistenceResponse createUser(CreateUserCommand command);
    UserJpaEntity findUserByEmail(String email);
}
