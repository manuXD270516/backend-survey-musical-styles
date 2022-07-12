package io.manudev.test3it.core.adapter.in.web;

import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;
import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMusicalStyleEndpoint {

    public final static String MUSICAL_STYLE_BASE_ROUTE = "musicalStyle";
    public final static String MUSICAL_STYLE_ID_PARAM = "{musicalStyleId}";

    public final static String MUSICAL_STYLE_TITLE_RESOURCE = "Musical Style Resource";
    public final static String DASHBOARD_PREFERENCES_ENDPOINT = "preferences";


    ResponseEntity<PersistenceResponse> createMusicalStyle(CreateMusicalStyleCommand command);
    ResponseEntity<List<MusicalStyleDto>> findAllMusicalStyles();
    ResponseEntity<List<MusicalStyleMetricDto>> getDashboardPreferences();
}
