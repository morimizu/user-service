package com.benjaminrperry.userservice


import com.benjaminrperry.userservice.dto.CreateUserDTO
import com.benjaminrperry.userservice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolationException

@SpringBootTest
class UserServiceSpec extends Specification {

    @Autowired
    UserService userService
    @Shared
    Random random = new Random()


    def createUserDto(username = "testUser", email = "test@test.com", password = "password") {
        CreateUserDTO.builder()
                .username(username)
                .email(email)
                .password(password)
                .build()
    }

    def 'create passes'() {
        given:
        def testUser = "testUser${random.nextInt()}"
        def testEmail = "${testUser}@test.com"
        def dto = createUserDto(testUser.toString(), testEmail.toString())

        when:
        def result = userService.registerNewUser(dto)

        then:
        noExceptionThrown()
        result

    }

    @Unroll
    def 'trigger validation exception - #test'() {
        given:

        def dto = createUserDto(usrname, email)

        when:
        userService.registerNewUser(dto)

        then:
        def error = thrown(ConstraintViolationException)


        where:
        test              | usrname                                  | email
        'username exists' | 'testUser'                               | "testUser${random.nextInt()}@test.com".toString()
        'email exists'    | "testUser${random.nextInt()}".toString() | 'testUser@test.com'
    }


}
