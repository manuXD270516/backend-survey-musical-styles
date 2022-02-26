package io.reflectoring.buckpal.account.application.port.in.user;

import io.reflectoring.buckpal.account.domain.dtos.UserDto;
import io.reflectoring.buckpal.common.utils.PersistenceResponse;

public interface CreateUserUseCase {

    PersistenceResponse create(CreateUserCommand createUserCommand);
}
