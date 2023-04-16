package com.benjaminrperry.userservice.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsernameConstraintValidator implements ConstraintValidator<ValidUsername, String> {

    private final UserValidator userValidator;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        log.debug("in Username validator");
        if (!userValidator.usernameValid(username)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A username " + username + " already exists")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
