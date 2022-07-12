package io.manudev.test3it.core.adapter.out.persistence;


import io.manudev.test3it.common.enums.PersistenceStatusEnum;
import io.manudev.test3it.common.utils.HelpersMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.UUID;

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
        if (HelpersMethods.isNull(entity.getStatus())) {
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
