package io.manudev.test3it.core.adapter.out.persistence.musicalstyle;


import io.manudev.test3it.common.utils.HelpersConstants;
import io.manudev.test3it.core.adapter.out.persistence.user.UserJpaEntity;
import io.manudev.test3it.core.adapter.out.persistence.BaseJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MusicalStyle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = HelpersConstants.FILTER_ACTIVE_RECORDS_FOR_PERSIST)
@SuperBuilder
public class MusicalStyleJpaEntity extends BaseJpaEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "MusicalStyle_User",
            joinColumns = {@JoinColumn(name = "musicalStyle_Id")},
            inverseJoinColumns = {@JoinColumn(name = "user_Id")}
    )
    private Set<UserJpaEntity> users = new HashSet<>();

}



