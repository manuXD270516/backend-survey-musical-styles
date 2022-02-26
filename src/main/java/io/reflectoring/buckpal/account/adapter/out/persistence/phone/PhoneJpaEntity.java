package io.reflectoring.buckpal.account.adapter.out.persistence.phone;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.reflectoring.buckpal.account.adapter.out.persistence.BaseJpaEntity;
import io.reflectoring.buckpal.account.adapter.out.persistence.user.UserJpaEntity;
import io.reflectoring.buckpal.common.utils.HelpersConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "Phone")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = HelpersConstants.FILTER_ACTIVE_RECORDS_FOR_PERSIST)
@SuperBuilder
public class PhoneJpaEntity extends BaseJpaEntity {

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "cityCode", nullable = false)
    private String cityCode;

    @Column(name = "contryCode", nullable = false)
    private String contryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(nullable = false)
    @JsonBackReference
    private UserJpaEntity userActive;



}



