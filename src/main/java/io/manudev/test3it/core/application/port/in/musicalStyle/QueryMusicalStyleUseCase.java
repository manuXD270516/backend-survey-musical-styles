package io.manudev.test3it.core.application.port.in.musicalStyle;

import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;

import java.util.List;

public interface QueryMusicalStyleUseCase {

    List<MusicalStyleDto> findAllMusicalStyles();

    List<MusicalStyleMetricDto> getDashobardPreferences();
}
