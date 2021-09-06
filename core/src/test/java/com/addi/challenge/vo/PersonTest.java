package com.addi.challenge.vo;

import com.addi.challenge.dummy.DummyPerson;
import com.addi.challenge.exception.ObjectValidatorException;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    @Test
    void createSuccessPerson() {
        Person person = DummyPerson.createOne();
        assertTrue(person.isValid);
    }

    @Test
    void invalidIdentificationPerson() {
        Person person = DummyPerson.createOne();
        assertThrows(ObjectValidatorException.class, () ->
            person.withIdentification(null));
    }

    @TestFactory
    Stream<DynamicTest> invalidBirthDatePerson() {

        Person person = DummyPerson.createOne();

        return Stream.of(
            "2020-12-21",
            "any date",
            "2020-12/21",
            "2020/12/21",
            "2020-21-01",
            "2020-12-21 14:25:56",
            null
        ).map(input -> DynamicTest.dynamicTest("failed :" + input,
            () -> assertThrows(ObjectValidatorException.class, () ->
                person.withBirthDate(input))
            )
        );
    }
}
