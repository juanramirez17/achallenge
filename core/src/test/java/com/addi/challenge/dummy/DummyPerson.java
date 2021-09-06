package com.addi.challenge.dummy;

import com.addi.challenge.entity.Person;

public class DummyPerson {

    public static Person createOne(){
        return Person.builder()
            .identification(DummyIdentification.createOne())
            .birthDate("2000-12-21")
            .firstName("Juan")
            .lastName("Dias")
            .email("juan@dis.com")
            .build();

    }
}
