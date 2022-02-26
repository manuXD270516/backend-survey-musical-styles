package io.reflectoring.buckpal.account.adapter.out.persistence.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.reflectoring.buckpal.account.adapter.out.persistence.BaseJpaEntity;
import io.reflectoring.buckpal.account.adapter.out.persistence.phone.PhoneJpaEntity;
import io.reflectoring.buckpal.common.utils.HelpersConstants;
import io.reflectoring.buckpal.common.utils.PasswordEncoder;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
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

    public UserJpaEntity addPhones(List<PhoneJpaEntity> phones){
        this.phones.addAll(phones);
        phones.stream()
                .forEach(p -> p.setUserActive(this));
        return this;
    }

    @PrePersist
    public void prePersistUser(){
        accessToken = UUID.randomUUID();
    }


}
