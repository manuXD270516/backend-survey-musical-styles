package io.manudev.test3it.core.application.port.out.musicalStyle;

import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;
import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;

import java.util.List;

public interface MusicalStylePort {

    PersistenceResponse createMusicalStyle(CreateMusicalStyleCommand command);
    List<MusicalStyleDto> findAllMusicalStyle();

    MusicalStyleDto findByName(String name);

    List<MusicalStyleMetricDto> getDashobardPreferences();
}
