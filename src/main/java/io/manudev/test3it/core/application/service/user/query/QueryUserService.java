package io.manudev.test3it.core.application.service.user.query;

import io.manudev.test3it.common.UseCase;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.application.port.in.user.QueryUserUseCase;
import io.manudev.test3it.core.application.port.out.user.UserPort;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class QueryUserService implements QueryUserUseCase {

    private final UserPort userPort;

    @Override
    public List<UserDto> findAllUsers() {
        return userPort.findAllUsers();
    }
}
