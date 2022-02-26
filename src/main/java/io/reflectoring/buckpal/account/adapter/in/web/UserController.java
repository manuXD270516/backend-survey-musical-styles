package io.reflectoring.buckpal.account.adapter.in.web;

import io.reflectoring.buckpal.account.application.port.in.user.CreateUserCommand;
import io.reflectoring.buckpal.account.application.port.in.user.CreateUserUseCase;
import io.reflectoring.buckpal.common.WebAdapter;
import io.reflectoring.buckpal.common.enums.ActionRequestEnum;
import io.reflectoring.buckpal.common.utils.CustomErrorType;
import io.reflectoring.buckpal.common.utils.PersistenceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static io.reflectoring.buckpal.account.adapter.in.web.IUserEndpoint.USER_TITLE_RESOURCE;
import static org.springframework.http.ResponseEntity.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping(path = IUserEndpoint.USER_BASE_ROUTE)
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<PersistenceResponse> createUser(@RequestBody CreateUserCommand command){
        try {
            PersistenceResponse response =createUserUseCase.create(command);
            if (response.getActionRequestEnum().equals(ActionRequestEnum.RESOURCE_ALREADY_EXISTS)){
                return badRequest().body(response);
            }
            return ok().body(response);
        } catch (Exception e){
            return CustomErrorType.badRequest(USER_TITLE_RESOURCE, e.getMessage());
        }
    }
}
