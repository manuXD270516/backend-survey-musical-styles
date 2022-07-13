package io.manudev.test3it.services;

import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.service.musicalStyle.command.CommandMusicalStyleService;
import io.manudev.test3it.core.application.service.musicalStyle.query.QueryMusicalStyleService;
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
public class MusicalStyleServiceUnitTest {

    @MockBean
    private QueryMusicalStyleService queryMusicalStyleService;
    @MockBean
    private CommandMusicalStyleService commandMusicalStyleService;


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


    @Test
    public void it_should_get_all_musical_styles_service_success(){
        Mockito.when(queryMusicalStyleService.findAllMusicalStyles()).thenReturn(musicalStylesMock);
        List<MusicalStyleDto> musicalStyles = queryMusicalStyleService.findAllMusicalStyles();
        Assert.assertEquals(musicalStyles.get(0).getName(), musicalStylesMock.get(0).getName());
    }

  @Test
    public void it_should_get_all_musical_styles_preferences_service_success(){
        Mockito.when(queryMusicalStyleService.getDashobardPreferences()).thenReturn(musicalStylesPreferencesMock);
        List<MusicalStyleMetricDto> preferences = queryMusicalStyleService.getDashobardPreferences();
        Assert.assertEquals(preferences.get(0).getTotalLikes(), musicalStylesPreferencesMock.get(0).getTotalLikes());
    }


    @Test
    public void it_should_create_musical_style_service_success(){
        String code = UUID.randomUUID().toString();
        CreateMusicalStyleCommand command = new CreateMusicalStyleCommand(code,"Pop latino", "Genero del pop latino");
        ResponseMusicalStyleDto dto = ResponseMusicalStyleDto.builder()
                .name("Pop Latino")
                .description("Genero del pop latino")
                .build();
        PersistenceResponse responseMock = new PersistenceResponse()
                .setResourceName(CreateMusicalStyleCommand.class.getSimpleName())
                .setActionRequestEnum(ActionRequestEnum.CREATE)
                .setData(dto);
        Mockito.when(commandMusicalStyleService.create(command)).thenReturn(responseMock);

        PersistenceResponse response = commandMusicalStyleService.create(command);
        Assert.assertEquals(response.getActionRequestEnum(), responseMock.getActionRequestEnum());
    }

}
