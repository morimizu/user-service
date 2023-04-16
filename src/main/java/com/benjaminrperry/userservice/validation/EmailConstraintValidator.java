package com.benjaminrperry.userservice.validation;

import java.lang.annotation.Annotation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {

    private final UserValidator userValidator;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        log.debug("in email validator");
        if (!userValidator.emailValid(email)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A email " + email + " already exists")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
