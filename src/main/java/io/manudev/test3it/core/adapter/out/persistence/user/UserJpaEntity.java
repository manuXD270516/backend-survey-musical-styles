package io.manudev.test3it.core.adapter.out.persistence.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.manudev.test3it.common.utils.HelpersConstants;
import io.manudev.test3it.core.adapter.out.persistence.BaseJpaEntity;
import io.manudev.test3it.core.adapter.out.persistence.musicalstyle.MusicalStyleJpaEntity;
import io.manudev.test3it.core.adapter.out.persistence.phone.PhoneJpaEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = HelpersConstants.FILTER_ACTIVE_RECORDS_FOR_PERSIST)
@SuperBuilder
public class UserJpaEntity extends BaseJpaEntity {

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userActive", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Builder.Default
    private List<PhoneJpaEntity> phones = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "accessToken", nullable = false)
    private UUID accessToken;


    @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<MusicalStyleJpaEntity> musicalStyles = new HashSet<>();


    public UserJpaEntity addMusicStyle(MusicalStyleJpaEntity musicalStyleJpaEntity) {
        this.musicalStyles.add(musicalStyleJpaEntity);
        musicalStyleJpaEntity.getUsers().add(this);
        return this;
    }

    public UserJpaEntity addPhones(List<PhoneJpaEntity> phones) {
        if (phones != null) {
            this.phones.addAll(phones);
            phones.stream()
                    .forEach(p -> p.setUserActive(this));

        }
        return this;
    }

    @PrePersist
    public void prePersistUser() {
        accessToken = UUID.randomUUID();
    }


}
