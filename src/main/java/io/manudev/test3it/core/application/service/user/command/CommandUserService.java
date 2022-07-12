package io.manudev.test3it.core.application.service.user.command;

import io.manudev.test3it.common.UseCase;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.adapter.out.persistence.user.UserJpaEntity;
import io.manudev.test3it.core.application.service.user.validator.UserValidator;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;
import io.manudev.test3it.core.application.port.in.user.CommandUserUseCase;
import io.manudev.test3it.core.application.port.out.user.UserPort;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CommandUserService implements CommandUserUseCase {

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

    @Override
    public PersistenceResponse registerMusicStyleOfUser(CreateUserMusicStyleCommand createUserMusicStyleCommand) {
        List<String> errors =  UserValidator.validateCreateUserMusicStyleCommand(createUserMusicStyleCommand);
        if (!errors.isEmpty()) {
            return new PersistenceResponse()
                    .setResourceName(CreateUserMusicStyleCommand.class.getSimpleName())
                    .setData(String.join("\n", errors))
                    .setActionRequestEnum(ActionRequestEnum.VALIDATION_ERROR);
        }
        return userPort.registerMusicStyleOfUser(createUserMusicStyleCommand);
    }
}
