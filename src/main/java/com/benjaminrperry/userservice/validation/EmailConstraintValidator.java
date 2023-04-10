package com.benjaminrperry.userservice.validation;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConstraintValidator implements ConstraintValidator<Annotation, String> {

    private final UserValidator userValidator;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (!userValidator.emailValid(email)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A email " + email + " already exists");
            return false;
        }
        return true;
    }

}
