package io.reflectoring.buckpal.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class User extends BaseDomain {

    public User(UUID id, Date createdAt, Date lastModifiedAt, String name, String surName, Long genderIdc, String email, String userName, String password, String secretQuestion, String secretAnswer, Long regionalIdc, Long officeIdc, String token, Long roleId) {
        super(id, createdAt, lastModifiedAt);
        this.name = name;
        this.surName = surName;
        this.genderIdc = genderIdc;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.regionalIdc = regionalIdc;
        this.officeIdc = officeIdc;
        this.token = token;
        this.roleId = roleId;
    }

    private String name;

    private String surName;

    private Long genderIdc;

    private String email;

    public String userName;

    public String password;

    private String secretQuestion;

    private String secretAnswer;

    private Long regionalIdc;

    private Long officeIdc;

    private String token;

    private Long roleId;
}
