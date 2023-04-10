package com.benjaminrperry.userservice.service

import com.benjaminrperry.userservice.IntegrationSpec
import com.benjaminrperry.userservice.dto.CreateUserDTO
import org.springframework.beans.factory.annotation.Autowired

import javax.validation.Validation

class UserServiceIntSpec extends IntegrationSpec {
    @Autowired
    UserService userService

    def 'register new user'() {
        given:
        def username = 'testUser'
        def email = 'test@test.com'
        def password = 'password'
        def dto = CreateUserDTO.builder()
                .username(username)
                .email(email)
                .password(password)
                .build()
        when:
        userService.registerNewUser(dto)

        then:
        noExceptionThrown()
    }

    def 'test validation'(){
        given:
        def validator = Validation.buildDefaultValidatorFactory().getValidator()
        def username = 'testUser'
        def email = 'test@test.com'
        def password = 'password'
        def dto = CreateUserDTO.builder()
                .username(username)
                .email(email)
                .password(password)
                .build()

        when:
        def violations = validator.validate(dto)

        then:
        violations.empty
    }
}
