package io.manudev.test3it.core.adapter.out.persistence.user;

import io.manudev.test3it.common.PersistenceAdapter;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PasswordEncoder;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.adapter.out.persistence.musicalstyle.MusicalStyleJpaEntity;
import io.manudev.test3it.core.adapter.out.persistence.musicalstyle.MusicalStyleRepository;
import io.manudev.test3it.core.adapter.out.persistence.phone.PhonePersistenceAdapter;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;
import io.manudev.test3it.core.application.port.out.user.UserPort;
import io.manudev.test3it.core.domain.dtos.responses.ResponseCreateMusicStyleUserDto;
import io.manudev.test3it.core.domain.dtos.responses.ResponseCreateUserDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPort {

    private final UserRepository userRepository;
    private final MusicalStyleRepository musicalStyleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PersistenceResponse createUser(CreateUserCommand command) {

        UserJpaEntity entityForCreate = mapCreateUserCommandToEntity(command);
        UserJpaEntity entityResult = userRepository.save(entityForCreate);
        return new PersistenceResponse()
                .setResourceName(CreateUserCommand.class.getSimpleName())
                .setActionRequestEnum(ActionRequestEnum.CREATE)
                .setData(ResponseCreateUserDto.mapEntityToDto(entityResult));
    }

    @Override
    public PersistenceResponse registerMusicStyleOfUser(CreateUserMusicStyleCommand command) {
        UserJpaEntity userFind = userRepository.findById(command.getUserId())
                .orElseThrow();
        MusicalStyleJpaEntity musicalFind = musicalStyleRepository.findById(command.getMusicalStyleId())
                .orElseThrow();

        userFind.addMusicStyle(musicalFind);
        UserJpaEntity entityResult = userRepository.save(userFind);

        return new PersistenceResponse()
                .setResourceName(CreateUserMusicStyleCommand.class.getSimpleName())
                .setActionRequestEnum(ActionRequestEnum.UPDATE)
                .setData(ResponseCreateMusicStyleUserDto.mapEntityToDto(entityResult));
    }


    @Override
    public UserJpaEntity findUserByEmail(String email) {
        Optional<UserJpaEntity> response = userRepository.findByEmail(email);
        return response.isPresent() ? response.get() : null;
        //.orElseThrow(() -> new NotDataFoundException("User with email: " + email + " Not found"));
    }

    @Override
    public List<UserDto> findAllUsers() {
        return mapEntitiesToDtoList(userRepository.findAll());
    }

    private static List<UserDto> mapEntitiesToDtoList(List<UserJpaEntity> entities) {
        return entities.stream()
                .map(entity -> mapEntityToDto(entity))
                .collect(Collectors.toList());
    }

    private static UserDto mapEntityToDto(UserJpaEntity entity) {
        return UserDto.builder()
                .userId(entity.getId())
                .fullname(entity.getFullname())
                .email(entity.getEmail())
                .build();
    }

    private UserJpaEntity mapCreateUserCommandToEntity(CreateUserCommand command) {
        UserJpaEntity x = UserJpaEntity.builder()
                .fullname(command.getFullname())
                .email(command.getEmail())
                .password(command.getPassword() != null ? passwordEncoder.password().encode(command.getPassword()) : null)
                //.phones(PhonePersistenceAdapter.mapDtosToEntities(command.getPhones()))
                .build()
                .addPhones(PhonePersistenceAdapter.mapDtosToEntities(command.getPhones()));
        return x;
    }
}
