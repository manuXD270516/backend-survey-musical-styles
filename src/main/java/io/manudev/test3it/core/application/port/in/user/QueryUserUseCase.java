package io.manudev.test3it.core.application.port.in.user;

import io.manudev.test3it.core.domain.dtos.UserDto;

import java.util.List;

public interface QueryUserUseCase {

    List<UserDto> findAllUsers();
}
