package com.addi.challenge.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthDateValidator implements ConstraintValidator<BirthDateConstraint, String> {

    String format;

    @Override
    public void initialize(BirthDateConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        format = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean valid = false;
        LocalDate date;
        try {
            date = LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            valid = date.isBefore(LocalDate.now().minusYears(18));
        } catch (Exception e) {
        }

        return valid;
    }
}
