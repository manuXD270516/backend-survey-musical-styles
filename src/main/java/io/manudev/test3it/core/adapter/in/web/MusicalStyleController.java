package io.manudev.test3it.core.adapter.in.web;

import io.manudev.test3it.common.WebAdapter;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.CustomErrorType;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;
import io.manudev.test3it.core.application.port.in.musicalStyle.CommandMusicalStyleUseCase;
import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;
import io.manudev.test3it.core.application.port.in.musicalStyle.QueryMusicalStyleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping(path = IMusicalStyleEndpoint.MUSICAL_STYLE_BASE_ROUTE)
public class MusicalStyleController implements IMusicalStyleEndpoint {

    private final QueryMusicalStyleUseCase queryMusicalStyleUseCase;
    private final CommandMusicalStyleUseCase commandMusicalStyleUseCase;

    @PostMapping
    @Override
    public ResponseEntity<PersistenceResponse> createMusicalStyle(@RequestBody CreateMusicalStyleCommand command) {
        try {
            PersistenceResponse response = commandMusicalStyleUseCase.create(command);
            if (response.getActionRequestEnum().equals(ActionRequestEnum.RESOURCE_ALREADY_EXISTS)) {
                return badRequest().body(response);
            }
            return ok().body(response);
        } catch (Exception e) {
            return CustomErrorType.badRequest(MUSICAL_STYLE_TITLE_RESOURCE, e.getMessage());
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<List<MusicalStyleDto>> findAllMusicalStyles() {
        return ok().body(queryMusicalStyleUseCase.findAllMusicalStyles());
    }

    @GetMapping(DASHBOARD_PREFERENCES_ENDPOINT)
    @Override
    public ResponseEntity<List<MusicalStyleMetricDto>> getDashboardPreferences() {
        return ok().body(queryMusicalStyleUseCase.getDashobardPreferences());
    }


}
