package io.manudev.test3it.core.adapter.in.web;

import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserEndpoint {

    public final static String USER_BASE_ROUTE = "user";
    public final static String USER_ID_PARAM = "{userId}";

    public final static String USER_TITLE_RESOURCE = "User Resource";

    public final static String USER_POST_MUSIC_STYLE_ROUTE = "musicStyle";




    ResponseEntity<PersistenceResponse> createUser(CreateUserCommand createUserCommand);
    ResponseEntity<PersistenceResponse> registerMusicStyleOfUser(CreateUserMusicStyleCommand createUserMusicStyleCommand);

    ResponseEntity<List<UserDto>> findAllUsers();
}
