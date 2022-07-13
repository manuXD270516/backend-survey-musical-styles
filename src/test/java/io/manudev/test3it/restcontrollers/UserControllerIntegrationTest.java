package io.manudev.test3it.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.adapter.in.web.UserController;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.service.user.command.CommandUserService;
import io.manudev.test3it.core.application.service.user.query.QueryUserService;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.domain.dtos.responses.ResponseCreateUserDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes= {}.class)
/*@SpringBootTest(classes = {ServletWebServerFactoryAutoConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"spring.cloud.config.enabled=false"})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc*/

/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Test3ItApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")*/
/*@SpringBootTest(classes = Test3ItApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration*/

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerIntegrationTest {

    @MockBean
    private QueryUserService queryUserService;
    @MockBean
    private CommandUserService commandUserService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    public List<UserDto> usersMock = Arrays.asList(
            UserDto.builder()
                    .userId(UUID.randomUUID().toString())
                    .fullname("manuel saavedra")
                    .email("manuel@gmail.com")
                    .password("21212DSA")
                    .build(),
            UserDto.builder()
                    .userId(UUID.randomUUID().toString())
                    .fullname("miguel perales")
                    .email("mick@gmail.com")
                    .password("1413daad")
                    .build()

    );

    @Test
    public void it_should_get_users_success() throws Exception {
        Mockito.when(queryUserService.findAllUsers()).thenReturn(usersMock);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user"))
                //.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].email", is("mick@gmail.com")));
    }


    @Test
    public void it_should_return_created_user_success() throws Exception {
        CreateUserCommand command = new CreateUserCommand("manuel", "manuel@gmail.com", "2121fdasdaA1*", new ArrayList<>());
        ResponseCreateUserDto dto = ResponseCreateUserDto.builder()
                .isActive(true)
                .build();
        PersistenceResponse response = new PersistenceResponse()
                .setResourceName(CreateUserCommand.class.getSimpleName())
                .setActionRequestEnum(ActionRequestEnum.CREATE)
                .setData(dto);
        Mockito.when(commandUserService.create(command)).thenReturn(response);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(command));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.actionRequestEnum", is(response.getActionRequestEnum().toString())));
    }
}
