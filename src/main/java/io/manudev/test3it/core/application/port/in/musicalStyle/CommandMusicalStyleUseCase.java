package io.manudev.test3it.core.application.port.in.musicalStyle;

import io.manudev.test3it.common.utils.PersistenceResponse;

public interface CommandMusicalStyleUseCase {

    PersistenceResponse create(CreateMusicalStyleCommand command);

}
