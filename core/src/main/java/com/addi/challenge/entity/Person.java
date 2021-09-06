package com.addi.challenge.entity;

import com.addi.challenge.constraint.BirthDateConstraint;
import com.addi.challenge.exception.ObjectValidatorException;
import com.addi.challenge.vo.Identification;
import com.addi.challenge.vo.ObjectValidator;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Value
@With
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Person extends ObjectValidator<Person> {

    @NotNull @Valid
    Identification identification;

    @NotNull
    @BirthDateConstraint(format = "yyyy-MM-dd")
    String birthDate;

    @NotNull @Size(min = 2, max = 40)
    String firstName;

    @NotNull @Size(min = 2, max = 40)
    String lastName;

    @Email
    String email;

    public Person(@NotNull @Valid Identification identification, @NotNull @Pattern(regexp = "yyyy-MM-dd") String birthDate, @NotNull @Size(min = 2, max = 40) String firstName, @NotNull @Size(min = 2, max = 40) String lastName, @Email String email) {
        this.identification = identification;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        validate(this);
        if (!isValid)
            throw new ObjectValidatorException(violations);
    }
}
