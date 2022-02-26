package io.reflectoring.buckpal.account.adapter.out.persistence;


import io.reflectoring.buckpal.common.enums.PersistenceStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.UUID;

import static io.reflectoring.buckpal.common.utils.HelpersMethods.isNull;

@Component
@RequiredArgsConstructor
public class ChangeLogListener {

    private String ip;
    {
        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @PrePersist
    public void prePersist(final BaseJpaEntity entity) {
        entity.setId(UUID.randomUUID().toString().toUpperCase());
        entity.setStatus(PersistenceStatusEnum
                .CREATED_OR_UPDATED
                .getValue());

    }

    @PreUpdate
    public void preUpdate(final BaseJpaEntity entity) {
        if (isNull(entity.getStatus())) {
            entity.setStatus(PersistenceStatusEnum
                    .CREATED_OR_UPDATED
                    .getValue());
        }
    }

    @PostPersist
    public void postPersistChangeLog(final BaseJpaEntity entity) {
    }

    @PostUpdate
    public void postUpdateChangeLog(final BaseJpaEntity entity) {
    }
}
