package com.benjaminrperry.userservice.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

@Component
@RequiredArgsConstructor
public class UsernameConstraintValidator implements ConstraintValidator<Annotation, String> {

    private final UserValidator userValidator;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (!userValidator.usernameValid(username)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A username " + username + " already exists");
            return false;
        }
        return true;
    }

}
