package io.reflectoring.buckpal.account.application.service.user;

import io.reflectoring.buckpal.account.adapter.out.persistence.user.UserJpaEntity;
import io.reflectoring.buckpal.account.application.port.in.user.CreateUserCommand;
import io.reflectoring.buckpal.account.application.port.in.user.CreateUserUseCase;
import io.reflectoring.buckpal.account.application.port.out.user.UserPort;
import io.reflectoring.buckpal.common.UseCase;
import io.reflectoring.buckpal.common.enums.ActionRequestEnum;
import io.reflectoring.buckpal.common.utils.HelpersMethods;
import io.reflectoring.buckpal.common.utils.PersistenceResponse;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CreateUserService implements CreateUserUseCase {

    private final UserPort userPort;


    @Override
    public PersistenceResponse create(CreateUserCommand createUserCommand) {
        List<String> errors =  UserValidator.validateCreateUserCommand(createUserCommand);
        if (!errors.isEmpty()) {
            return new PersistenceResponse()
                    .setResourceName(CreateUserCommand.class.getSimpleName())
                    .setData(String.join("\n", errors))
                    .setActionRequestEnum(ActionRequestEnum.VALIDATION_ERROR);
        }
        UserJpaEntity userFind = userPort.findUserByEmail(createUserCommand.getEmail());
        if (userFind != null){
            return new PersistenceResponse()
                    .setResourceName(CreateUserCommand.class.getSimpleName())
                    .setData("El correo ya fue registrado")
                    .setActionRequestEnum(ActionRequestEnum.RESOURCE_ALREADY_EXISTS);
        }
        return userPort.createUser(createUserCommand);
    }
}
