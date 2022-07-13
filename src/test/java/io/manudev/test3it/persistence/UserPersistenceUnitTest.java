package io.manudev.test3it.persistence;

import io.manudev.test3it.common.enums.ActionRequestEnum;
import io.manudev.test3it.common.utils.PersistenceResponse;
import io.manudev.test3it.core.adapter.out.persistence.user.UserPersistenceAdapter;
import io.manudev.test3it.core.adapter.out.persistence.user.UserRepository;
import io.manudev.test3it.core.application.port.in.user.CreateUserCommand;
import io.manudev.test3it.core.domain.dtos.UserDto;
import io.manudev.test3it.core.domain.dtos.responses.ResponseCreateUserDto;
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
public class UserPersistenceUnitTest {

    @MockBean
    private UserPersistenceAdapter userPersistenceAdapter;

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
    public void it_should_get_all_users_persistence_success(){
        Mockito.when(userPersistenceAdapter.findAllUsers()).thenReturn(usersMock);
        List<UserDto> users = userPersistenceAdapter.findAllUsers();
        Assert.assertEquals(users.get(1).getUserId(), usersMock.get(1).getUserId());
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

        Mockito.when(userPersistenceAdapter.createUser(command)).thenReturn(responseMock);

        PersistenceResponse response = userPersistenceAdapter.createUser(command);
        Assert.assertEquals(response.getActionRequestEnum(), responseMock.getActionRequestEnum());
    }

}
