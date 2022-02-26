package io.reflectoring.buckpal.account.adapter.out.persistence.user;

import io.reflectoring.buckpal.account.adapter.out.persistence.phone.PhonePersistenceAdapter;
import io.reflectoring.buckpal.account.application.port.in.user.CreateUserCommand;
import io.reflectoring.buckpal.account.application.port.out.user.UserPort;
import io.reflectoring.buckpal.account.domain.dtos.responses.ResponseCreateUserDto;
import io.reflectoring.buckpal.account.domain.exception.NotDataFoundException;
import io.reflectoring.buckpal.common.PersistenceAdapter;
import io.reflectoring.buckpal.common.enums.ActionRequestEnum;
import io.reflectoring.buckpal.common.utils.PasswordEncoder;
import io.reflectoring.buckpal.common.utils.PersistenceResponse;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPort {

    private final UserRepository userRepository;
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
    public UserJpaEntity findUserByEmail(String email) {
        Optional<UserJpaEntity> response = userRepository.findByEmail(email);
        return response.isPresent() ? response.get() : null;
        //.orElseThrow(() -> new NotDataFoundException("User with email: " + email + " Not found"));
    }


    private UserJpaEntity mapCreateUserCommandToEntity(CreateUserCommand command) {
        UserJpaEntity x = UserJpaEntity.builder()
                .fullname(command.getFullname())
                .email(command.getEmail())
                .password(passwordEncoder.password().encode(command.getPassword()))
                //.phones(PhonePersistenceAdapter.mapDtosToEntities(command.getPhones()))
                .build()
                .addPhones(PhonePersistenceAdapter.mapDtosToEntities(command.getPhones()));
        return x;
    }
}
