package io.manudev.test3it.core.adapter.out.persistence.phone;

import org.springframework.data.repository.CrudRepository;


public interface PhoneRepository extends CrudRepository<PhoneJpaEntity, String> {
}
