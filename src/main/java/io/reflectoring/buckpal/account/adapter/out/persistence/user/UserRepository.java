package io.reflectoring.buckpal.account.adapter.out.persistence.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository  extends CrudRepository<UserJpaEntity, String> {

    Optional<UserJpaEntity> findByEmail(String email);
}
