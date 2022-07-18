package io.manudev.test3it.core.adapter.out.persistence.phone;

import io.manudev.test3it.common.PersistenceAdapter;
import io.manudev.test3it.core.application.port.out.phone.PhonePort;
import io.manudev.test3it.core.domain.dtos.PhoneDto;
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
        if (dtos!=null) {
            return dtos.stream()
                    .map(currentDto -> mapDtoToEntity(currentDto))
                    .collect(Collectors.toList());
        }
        return null;

    }

}
