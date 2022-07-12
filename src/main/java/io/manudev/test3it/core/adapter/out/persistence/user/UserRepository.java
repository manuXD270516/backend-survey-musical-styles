package io.manudev.test3it.core.adapter.out.persistence.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserJpaEntity, String> {

    List<UserJpaEntity> findAll();

    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findById(String id);

}
