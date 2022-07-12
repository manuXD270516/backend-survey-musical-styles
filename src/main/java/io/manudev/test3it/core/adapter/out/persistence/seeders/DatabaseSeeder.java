package io.manudev.test3it.core.adapter.out.persistence.seeders;

import io.manudev.test3it.core.adapter.out.persistence.musicalstyle.MusicalStyleJpaEntity;
import io.manudev.test3it.core.adapter.out.persistence.musicalstyle.MusicalStyleRepository;
import io.manudev.test3it.core.adapter.out.persistence.user.UserJpaEntity;
import io.manudev.test3it.core.adapter.out.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {


    private final UserRepository userRepository;
    private final MusicalStyleRepository musicalStyleRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsers();
        seedMusicalStyle();
    }


    public void seedMusicalStyle() {
        List<UserJpaEntity> users = Arrays.asList(
                UserJpaEntity.builder()
                        .fullname("Manuel Saavedra")
                        .email("manuel@gmail.com")
                        .password("313DF23")
                        .build()
        );
        userRepository.saveAll(users);
    }

    public void seedUsers() {
        List<MusicalStyleJpaEntity> musicStyles = Arrays.asList(
                MusicalStyleJpaEntity.builder()
                        .code(UUID.randomUUID().toString())
                        .name("Rock")
                        .description("Genero del Rock")
                        .build(),
                MusicalStyleJpaEntity.builder()
                        .code(UUID.randomUUID().toString())
                        .name("Jazz")
                        .description("Genero del Jazz")
                        .build()
        );
        musicalStyleRepository.saveAll(musicStyles);
    }

}
