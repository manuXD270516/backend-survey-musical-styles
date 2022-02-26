package io.reflectoring.buckpal.account.adapter.out.persistence.phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface PhoneRepository extends CrudRepository<PhoneJpaEntity, String> {
}
