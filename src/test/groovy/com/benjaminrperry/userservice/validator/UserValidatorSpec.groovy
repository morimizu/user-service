package com.benjaminrperry.userservice.validator

import com.benjaminrperry.userservice.repository.UserRepository
import com.benjaminrperry.userservice.validation.UserValidator
import spock.lang.Specification
import spock.lang.Unroll

class UserValidatorSpec extends Specification {
    def userRepo = Mock(UserRepository)
    def userValidator = new UserValidator(userRepo)

    @Unroll
    def 'username - #test'() {
        given:
        def username = 'testUser'
        userRepo.existsByUsername(username) >> exists

        when:
        def result = userValidator.usernameValid(username)

        then:
        result == passes

        where:
        test                           | exists | passes
        'non existing username passes' | false  | true
        'existing username fails'      | true   | false
    }

    @Unroll
    def 'email - #test'() {
        given:
        def email = 'testUser@test.com'
        userRepo.existsByEmail(email) >> exists

        when:
        def result = userValidator.emailValid(email)

        then:
        result == passes

        where:
        test                        | exists | passes
        'non existing email passes' | false  | true
        'existing email fails'      | true   | false
    }

}
