package io.manudev.test3it.core.application.service.musicalStyle.query;

import io.manudev.test3it.core.application.port.in.musicalStyle.QueryMusicalStyleUseCase;
import io.manudev.test3it.core.application.port.out.musicalStyle.MusicalStylePort;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class QueryMusicalStyleService implements QueryMusicalStyleUseCase {

    private final MusicalStylePort musicalStylePort;

    @Override
    public List<MusicalStyleDto> findAllMusicalStyles() {
        return musicalStylePort.findAllMusicalStyle();
    }

    @Override
    public List<MusicalStyleMetricDto> getDashobardPreferences() {
        return musicalStylePort.getDashobardPreferences();
    }
}
