package com.addi.challenge.vo;

import com.addi.challenge.dummy.DummyIdentification;
import com.addi.challenge.exception.ObjectValidatorException;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdentificationTest {

    @Test
    void createSuccessIdentification() {
        Identification identification = DummyIdentification.createOne();
        assertTrue(identification.isValid);
    }

    @TestFactory
    Stream<DynamicTest> invalidIdNumberIdentification() {

        Identification identification = DummyIdentification.createOne();

        return Stream.of(
            "1234",
            "1234567890123456",
            null,
            "aseerr",
            "-45582445"
        ).map(input -> DynamicTest.dynamicTest("failed :" + input,
            () -> assertThrows(ObjectValidatorException.class, () ->
                identification.withIdNumber(input))
            )
        );
    }
}
