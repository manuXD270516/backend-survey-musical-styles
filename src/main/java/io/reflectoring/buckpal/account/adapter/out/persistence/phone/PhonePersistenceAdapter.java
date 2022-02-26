package io.reflectoring.buckpal.account.adapter.out.persistence.phone;

import io.reflectoring.buckpal.account.application.port.out.phone.PhonePort;
import io.reflectoring.buckpal.account.domain.dtos.PhoneDto;
import io.reflectoring.buckpal.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class PhonePersistenceAdapter implements PhonePort {

    private final PhoneRepository phoneRepository;

    public static PhoneJpaEntity mapDtoToEntity(PhoneDto dto){
        PhoneJpaEntity x =PhoneJpaEntity.builder()
                .number(dto.getNumber())
                .cityCode(dto.getCityCode())
                .contryCode(dto.getContryCode())
                .build();
        return x;
    }

    public static List<PhoneJpaEntity> mapDtosToEntities(List<PhoneDto> dtos){
        return dtos.stream()
                .map(currentDto -> mapDtoToEntity(currentDto))
                .collect(Collectors.toList());
    }

}
