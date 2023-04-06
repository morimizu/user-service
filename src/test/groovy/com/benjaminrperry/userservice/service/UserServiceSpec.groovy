package com.benjaminrperry.userservice.service

import com.benjaminrperry.userservice.dto.CreateUserDTO
import com.benjaminrperry.userservice.mock.MockUserRepo
import com.benjaminrperry.userservice.repository.UserRepository
import spock.lang.Specification

class UserServiceSpec extends Specification {
    def userRepo = new MockUserRepo()
    def userService = new UserServiceImpl(userRepo as UserRepository)


    def 'user returned when registered'() {
        given:
        def username = 'test1'
        def email = 'test@test.com'
        def password = 'testpasss'
        def dto = CreateUserDTO.builder()
                .username(username)
                .email(email)
                .password(password)
                .build()

        when:
        def result = userService.registerNewUser(dto)

        then:
        result.username == username
        result.email == email
        result.creationDate != null
    }


}