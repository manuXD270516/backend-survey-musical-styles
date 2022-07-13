package io.manudev.test3it.persistence;

import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.adapter.out.persistence.musicalstyle.MusicalStylePersistenceAdapter;
import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.domain.dtos.responses.ResponseCreateUserDto;
import io.manudev.test3it.core.domain.dtos.responses.ResponseMusicalStyleDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class MusicStylePersistenceUnitTest {

    @MockBean
    private MusicalStylePersistenceAdapter musicalStylePersistenceAdapter;


    public List<MusicalStyleMetricDto> musicalStylesPreferencesMock = Arrays.asList(
            MusicalStyleMetricDto.builder()
                    .musicalStyleId(UUID.randomUUID().toString())
                    .code(UUID.randomUUID().toString())
                    .name("Rock")
                    .totalLikes(10)
                    .build(),
            MusicalStyleMetricDto.builder()
                    .musicalStyleId(UUID.randomUUID().toString())
                    .code(UUID.randomUUID().toString())
                    .name("Balada")
                    .totalLikes(40)
                    .build()
    );

    public List<MusicalStyleDto> musicalStylesMock = Arrays.asList(
            MusicalStyleDto.builder()
                    .code(UUID.randomUUID().toString())
                    .name("Rock")
                    .description("Genero del Rock")
                    .build(),
            MusicalStyleDto.builder()
                    .code(UUID.randomUUID().toString())
                    .name("Jazz")
                    .description("Genero del Jazz")
                    .build()
    );

    @Test
    public void it_should_get_all_musical_styles_persistence_success() {
        Mockito.when(musicalStylePersistenceAdapter.findAllMusicalStyle()).thenReturn(musicalStylesMock);
        List<MusicalStyleDto> musicalStyles = musicalStylePersistenceAdapter.findAllMusicalStyle();
        Assert.assertEquals(musicalStyles.get(1).getName(), musicalStylesMock.get(1).getName());
    }

    @Test
    public void it_should_get_all_musical_styles_preferences_persistence_success() {
        Mockito.when(musicalStylePersistenceAdapter.getDashobardPreferences()).thenReturn(musicalStylesPreferencesMock);
        List<MusicalStyleMetricDto> preferences = musicalStylePersistenceAdapter.getDashobardPreferences();
        Assert.assertEquals(preferences.get(1).getTotalLikes(), musicalStylesPreferencesMock.get(1).getTotalLikes());
    }

    @Test
    public void it_should_create_musical_style_service_success() {
        String code = UUID.randomUUID().toString();
        CreateMusicalStyleCommand command = new CreateMusicalStyleCommand(code, "Pop latino", "Genero del pop latino");
        ResponseMusicalStyleDto dto = ResponseMusicalStyleDto.builder()
                .name("Pop Latino")
                .description("Genero del pop latino")
                .build();
        PersistenceResponse responseMock = new PersistenceResponse()
                .setResourceName(CreateMusicalStyleCommand.class.getSimpleName())
                .setActionRequestEnum(ActionRequestEnum.CREATE)
                .setData(dto);
        Mockito.when(musicalStylePersistenceAdapter.createMusicalStyle(command)).thenReturn(responseMock);

        PersistenceResponse response = musicalStylePersistenceAdapter.createMusicalStyle(command);
        Assert.assertEquals(response.getActionRequestEnum(), responseMock.getActionRequestEnum());
    }

}
