package io.manudev.test3it.core.application.service.musicalStyle.command;

import io.manudev.test3it.common.UseCase;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.application.service.musicalStyle.validator.MusicalStyleValidator;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.core.application.port.in.musicalStyle.CommandMusicalStyleUseCase;
import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;
import io.manudev.test3it.core.application.port.out.musicalStyle.MusicalStylePort;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class CommandMusicalStyleService implements CommandMusicalStyleUseCase {

    private final MusicalStylePort musicalStylePort;

    @Override
    public PersistenceResponse create(CreateMusicalStyleCommand command) {
        List<String> errors = MusicalStyleValidator.validateCreateMusicalStyleCommand(command);
        if (!errors.isEmpty()) {
            return new PersistenceResponse()
                    .setResourceName(CreateMusicalStyleCommand.class.getSimpleName())
                    .setData(String.join("\n", errors))
                    .setActionRequestEnum(ActionRequestEnum.VALIDATION_ERROR);
        }
        MusicalStyleDto musicalStyleFind = musicalStylePort.findByName(command.getName());
        if (musicalStyleFind != null) {
            return new PersistenceResponse()
                    .setResourceName(CreateMusicalStyleCommand.class.getSimpleName())
                    .setData("Nombre de genero musical ya fue registrado")
                    .setActionRequestEnum(ActionRequestEnum.RESOURCE_ALREADY_EXISTS);
        }
        return musicalStylePort.createMusicalStyle(command);
    }

    /*@Override
    public PersistenceResponse registerMusicStyleOfUser(CreateUserMusicStyleCommand createUserMusicStyleCommand) {
        List<String> errors =  UserValidator.validateCreateUserMusicStyleCommand(createUserMusicStyleCommand);
        if (!errors.isEmpty()) {
            return new PersistenceResponse()
                    .setResourceName(CreateUserMusicStyleCommand.class.getSimpleName())
                    .setData(String.join("\n", errors))
                    .setActionRequestEnum(ActionRequestEnum.VALIDATION_ERROR);
        }
        return userPort.registerMusicStyleOfUser(createUserMusicStyleCommand);
    }*/
}
