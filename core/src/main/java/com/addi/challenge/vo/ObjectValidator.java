package com.addi.challenge.vo;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ObjectValidator<T> {

    protected Boolean isValid = false;
    protected List<Violation> violations = new ArrayList<>();
    private ValidatorFactory factory;

    protected void validate(T t) {
        factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        violations = validator.validate(t)
            .stream().map(cv -> new Violation(t.getClass().getSimpleName(), cv.getPropertyPath().toString(), Optional.ofNullable(cv.getInvalidValue()).map(Object::toString).orElseGet(() -> null), cv.getPropertyPath().toString() + " : " + cv.getMessage()))
            .collect(Collectors.toList());
        if (violations.isEmpty())
            isValid = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectValidator<?> objectValidator = (ObjectValidator<?>) o;
        return isValid.equals(objectValidator.isValid) && violations.equals(objectValidator.violations);
    }

    @Override
    public String toString() {
        return "Entity{" +
            "isValid=" + isValid +
            ", violations=" + violations +
            '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(isValid, violations, factory);
    }
}
