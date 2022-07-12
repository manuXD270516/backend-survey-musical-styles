package io.manudev.test3it.core.adapter.in.web;

import io.manudev.test3it.common.WebAdapter;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.CustomErrorType;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.port.in.user.QueryUserUseCase;
import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;
import io.manudev.test3it.core.application.port.in.user.CommandUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping(path = IUserEndpoint.USER_BASE_ROUTE)
public class UserController implements IUserEndpoint {

    private final CommandUserUseCase commandUserUseCase;
    private final QueryUserUseCase queryUserUseCase;

    @PostMapping
    @Override
    public ResponseEntity<PersistenceResponse> createUser(@RequestBody CreateUserCommand command) {
        try {
            PersistenceResponse response = commandUserUseCase.create(command);
            if (response.getActionRequestEnum().equals(ActionRequestEnum.RESOURCE_ALREADY_EXISTS)) {
                return badRequest().body(response);
            }
            return ok().body(response);
        } catch (Exception e) {
            return CustomErrorType.badRequest(USER_TITLE_RESOURCE, e.getMessage());
        }
    }

    @PostMapping(USER_POST_MUSIC_STYLE_ROUTE)
    @Override
    public ResponseEntity<PersistenceResponse> registerMusicStyleOfUser(@RequestBody CreateUserMusicStyleCommand createUserMusicStyleCommand) {
        try {
            PersistenceResponse response = commandUserUseCase.registerMusicStyleOfUser(createUserMusicStyleCommand);
            if (response.getActionRequestEnum().equals(ActionRequestEnum.RESOURCE_ALREADY_EXISTS)) {
                return badRequest().body(response);
            }
            return ok().body(response);
        } catch (Exception e) {
            return CustomErrorType.badRequest(USER_TITLE_RESOURCE, e.getMessage());
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return ok().body(queryUserUseCase.findAllUsers());
    }
}
