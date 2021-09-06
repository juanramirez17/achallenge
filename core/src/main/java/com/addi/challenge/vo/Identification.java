package com.addi.challenge.vo;

import com.addi.challenge.exception.ObjectValidatorException;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Value
@With
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Identification extends ObjectValidator<Identification> {

    @NotNull @Positive @Size(min = 5, max = 15)
    String idNumber;
    IdTypeEnum type;

    public Identification(@Positive @Size(min = 5, max = 15) String idNumber, IdTypeEnum type) {
        this.idNumber = idNumber;
        this.type = type;
        validate(this);
        if (!isValid)
            throw new ObjectValidatorException(violations);
    }
}
