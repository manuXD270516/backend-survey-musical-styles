package io.manudev.test3it.core.adapter.out.persistence.musicalstyle;

import io.manudev.test3it.common.PersistenceAdapter;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.application.port.out.musicalStyle.MusicalStylePort;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;
import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;
import io.manudev.test3it.core.application.port.in.userMusicStyle.CreateUserMusicStyleCommand;
import io.manudev.test3it.core.domain.dtos.responses.ResponseMusicalStyleDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class MusicalStylePersistenceAdapter implements MusicalStylePort {

    private final MusicalStyleRepository musicalStyleRepository;

    @Override
    public PersistenceResponse createMusicalStyle(CreateMusicalStyleCommand command) {
        MusicalStyleJpaEntity entityForCreate = mapCommandToEntity(command);
        MusicalStyleJpaEntity entityResult = musicalStyleRepository.save(entityForCreate);
        return new PersistenceResponse()
                .setResourceName(CreateUserMusicStyleCommand.class.getSimpleName())
                .setActionRequestEnum(ActionRequestEnum.CREATE)
                .setData(ResponseMusicalStyleDto.mapEntityToDto(entityResult));
    }

    @Override
    public List<MusicalStyleDto> findAllMusicalStyle() {
        List<MusicalStyleJpaEntity> entities = musicalStyleRepository.findAll();
        return mapMusicalStyleEntitiesToDtoList(entities);
    }

    @Override
    public MusicalStyleDto findByName(String name) {
        Optional<MusicalStyleJpaEntity> entityFind = musicalStyleRepository.findByName(name);
        return entityFind.isPresent() ? MusicalStylePersistenceAdapter.mapEntityToDto(entityFind.get()) : null;
    }

    @Override
    public List<MusicalStyleMetricDto> getDashobardPreferences() {
        List<MusicalStyleJpaEntity> entities = musicalStyleRepository.getMetricPreferencesUsersByMusicalStyle();
        return mapEntitesToMetricsDtoList(entities);
    }

    private static List<MusicalStyleMetricDto> mapEntitesToMetricsDtoList(List<MusicalStyleJpaEntity> entities) {
        return entities.stream()
                .map(entity -> mapEntityToMetricDto(entity))
                .collect(Collectors.toList());
    }

    private static MusicalStyleMetricDto mapEntityToMetricDto(MusicalStyleJpaEntity entity) {
        return MusicalStyleMetricDto.builder()
                .musicalStyleId(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .totalLikes(entity.getUsers().size())
                .build();
    }

    private static MusicalStyleJpaEntity mapCommandToEntity(CreateMusicalStyleCommand command) {
        return MusicalStyleJpaEntity.builder()
                .code(command.getCode())
                .name(command.getName())
                .description(command.getDescription())
                .build();
    }

    private static MusicalStyleDto mapEntityToDto(MusicalStyleJpaEntity entity) {
        return MusicalStyleDto.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    /*@Override
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
        MusicalStyleEntity musicalFind = musicalStyleRepository.findById(command.getMusicalStyleId())
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


    private UserJpaEntity mapCreateUserCommandToEntity(CreateUserCommand command) {
        UserJpaEntity x = UserJpaEntity.builder()
                .fullname(command.getFullname())
                .email(command.getEmail())
                .password(passwordEncoder.password().encode(command.getPassword()))
                //.phones(PhonePersistenceAdapter.mapDtosToEntities(command.getPhones()))
                .build()
                .addPhones(PhonePersistenceAdapter.mapDtosToEntities(command.getPhones()));
        return x;
    }*/

    private MusicalStyleDto mapMusicalStyleEntityToDto(MusicalStyleJpaEntity entity) {
        return MusicalStyleDto.builder()
                .musicalStyleId(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    private List<MusicalStyleDto> mapMusicalStyleEntitiesToDtoList(List<MusicalStyleJpaEntity> entities) {
        return entities.stream()
                .map(entity -> mapMusicalStyleEntityToDto(entity))
                .collect(Collectors.toList());
    }

}
