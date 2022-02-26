package io.reflectoring.buckpal.account.adapter.in.web;

import io.reflectoring.buckpal.account.application.port.in.user.CreateUserCommand;
import io.reflectoring.buckpal.account.domain.dtos.UserDto;
import io.reflectoring.buckpal.common.utils.PersistenceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserEndpoint {

    public final static String USER_BASE_ROUTE = "user";
    public final static String USER_ID_PARAM = "{userId}";
    public final static String USER_TITLE_RESOURCE = "User Resource";

    ResponseEntity<PersistenceResponse> createUser(CreateUserCommand createUserCommand);
}
