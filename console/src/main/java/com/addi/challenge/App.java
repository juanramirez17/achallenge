package com.addi.challenge;

import com.addi.challenge.vo.IdTypeEnum;
import com.addi.challenge.vo.Identification;

public class App {

    public static void main(String[] arg) {
        Identification a = Identification.builder()
            .idNumber("1234567")
            .type(IdTypeEnum.CC)
            .build();
        System.out.println("ADDI " + a);
    }
}
