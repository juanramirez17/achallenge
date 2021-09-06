package com.addi.challenge.exception;

import com.addi.challenge.vo.Violation;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class ObjectValidatorException extends RuntimeException {

    private static String MESSAGE = "Validate the object fields";
    public List<Violation> violations;

    public ObjectValidatorException(List<Violation> violations) {
        super(MESSAGE);
        this.violations = violations;
    }
}
