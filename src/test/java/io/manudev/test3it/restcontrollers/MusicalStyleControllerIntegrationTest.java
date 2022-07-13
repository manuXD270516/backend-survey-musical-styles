package io.manudev.test3it.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.adapter.in.web.MusicalStyleController;
import io.manudev.test3it.core.application.port.in.musicalStyle.CreateMusicalStyleCommand;
import io.manudev.test3it.core.application.service.musicalStyle.command.CommandMusicalStyleService;
import io.manudev.test3it.core.application.service.musicalStyle.query.QueryMusicalStyleService;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;
import io.manudev.test3it.core.domain.dtos.responses.ResponseMusicalStyleDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MusicalStyleController.class)
public class MusicalStyleControllerIntegrationTest {

    @MockBean
    private QueryMusicalStyleService queryMusicalStyleService;
    @MockBean
    private CommandMusicalStyleService commandMusicalStyleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


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
    public void it_should_get_musical_styles_success() throws Exception {
        Mockito.when(queryMusicalStyleService.findAllMusicalStyles()).thenReturn(musicalStylesMock);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/musicalStyle"))
                //.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("Jazz")));
    }


    @Test
    public void it_should_get_musical_styles_preferences_success() throws Exception {
        Mockito.when(queryMusicalStyleService.getDashobardPreferences()).thenReturn(musicalStylesPreferencesMock);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/musicalStyle/preferences"))
                //.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].totalLikes", is(10)));
    }


    @Test
    public void it_should_return_created_musical_style_sucess() throws Exception {
        String code = UUID.randomUUID().toString();
        CreateMusicalStyleCommand command = new CreateMusicalStyleCommand(code, "Pop latino", "Genero del pop latino");

        ResponseMusicalStyleDto dto = ResponseMusicalStyleDto.builder()
                .name("Pop Latino")
                .description("Genero del pop latino")
                .build();
        PersistenceResponse response = new PersistenceResponse()
                .setResourceName(CreateMusicalStyleCommand.class.getSimpleName())
                .setActionRequestEnum(ActionRequestEnum.CREATE)
                .setData(dto);
        Mockito.when(commandMusicalStyleService.create(command)).thenReturn(response);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/musicalStyle")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(command));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.actionRequestEnum", is(response.getActionRequestEnum().toString())));
    }
}
