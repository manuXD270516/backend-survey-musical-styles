package io.manudev.test3it.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.application.service.musicalStyle.command.CommandMusicalStyleService;
import io.manudev.test3it.core.application.service.musicalStyle.query.QueryMusicalStyleService;
import io.manudev.test3it.core.application.service.user.command.CommandUserService;
import io.manudev.test3it.core.application.service.user.query.QueryUserService;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.domain.dtos.responses.ResponseCreateUserDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class UserServiceUnitTest {

    @MockBean
    private QueryUserService queryUserService;
    @MockBean
    private CommandUserService commandUserService;



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
    public void it_should_get_all_users_service_success(){
        Mockito.when(queryUserService.findAllUsers()).thenReturn(usersMock);
        List<UserDto> users = queryUserService.findAllUsers();
        Assert.assertEquals(users.get(0).getUserId(), usersMock.get(0).getUserId());
    }


    @Test
    public void it_should_create_user_service_success(){
        CreateUserCommand command = new CreateUserCommand("manuel", "manuel@gmail.com", "2121fdasdaA1*", new ArrayList<>());
        ResponseCreateUserDto dto = ResponseCreateUserDto.builder()
                .isActive(true)
                .build();
        PersistenceResponse responseMock = new PersistenceResponse()
                .setResourceName(CreateUserCommand.class.getSimpleName())
                .setActionRequestEnum(ActionRequestEnum.CREATE)
                .setData(dto);

        Mockito.when(commandUserService.create(command)).thenReturn(responseMock);

        PersistenceResponse response = commandUserService.create(command);
        Assert.assertEquals(response.getActionRequestEnum(), responseMock.getActionRequestEnum());
    }

}
