package com.addi.challenge.vo;

import lombok.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Value
@With
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Identification extends ObjectValidator<Identification>{

    @Positive @Size(min = 5, max = 15)
    String idNumber;
    IdTypeEnum type;
}
