package io.manudev.test3it.core.adapter.out.persistence.musicalstyle;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MusicalStyleRepository extends CrudRepository<MusicalStyleJpaEntity, String> {

    List<MusicalStyleJpaEntity> findAll();

    Optional<MusicalStyleJpaEntity> findById(String id);

    Optional<MusicalStyleJpaEntity> findByName(String name);

    @Query(value = "SELECT ms FROM MusicalStyleJpaEntity ms JOIN ms.users us")
    List<MusicalStyleJpaEntity> getMetricPreferencesUsersByMusicalStyle();
}
