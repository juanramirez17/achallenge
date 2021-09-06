package com.addi.challenge.dummy;

import com.addi.challenge.vo.IdTypeEnum;
import com.addi.challenge.vo.Identification;

public class DummyIdentification {

    public static Identification createOne() {
        return Identification.builder()
            .idNumber("1054987578")
            .type(IdTypeEnum.CC)
            .build();
    }
}
