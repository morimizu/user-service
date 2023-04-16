package com.benjaminrperry.userservice

import com.benjaminrperry.userservice.dto.CreateUserDTO
import com.benjaminrperry.userservice.entity.UserJpa
import com.benjaminrperry.userservice.service.UserService
import com.benjaminrperry.userservice.validation.UserValidator
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import spock.mock.DetachedMockFactory

import static org.hamcrest.Matchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = TestConfig.class)
@WebMvcTest
class UserControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc


    @Shared
    ObjectMapper objectMapper = new ObjectMapper()

    @Shared
    Random random = new Random()

    @Autowired
    UserService userService

    @Autowired
    UserValidator userValidator


    @Unroll
    def "validation works correctly- #test"() {
        given:
        def dto = randomUserDto()
        userValidator.usernameValid(_) >> userValid
        userValidator.emailValid(_) >> emailValid
        userService.registerNewUser(_) >> new UserJpa()

        when:
        def result = mockMvc.perform(post("http://localhost:8080/users", dto))

        then:
        expectations.stream().forEach { result::andExpect { it } }


        where:
        test               | userValid | emailValid | expectations
        'dto is valid'     | true      | true       | [status().isOk()]
        'invalid username' | false     | true       | [status().isBadRequest(),
                                                       status().reason(containsString("already exists"))]
        'invalid email'    | true      | false      | [status().isBadRequest(),
                                                       status().reason(containsString("already exists"))]
    }

    def randomUserDto() {
        def username = "testUser${random.nextInt()}".toString()
        def email = "${username}@test.com"
        CreateUserDTO.builder()
                .username(username)
                .email(email)
                .password('password')
                .build()
    }

    def post(uri, content) {
        post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(content))
    }

    def toJson(content) {
        new JsonBuilder(content).toString()
    }

    @TestConfiguration
    static class TestConfig {
        def mockFactory = new DetachedMockFactory()

        @Bean
        UserService userService() {
            return mockFactory.Mock(UserService)
        }

        @Bean
        UserValidator userValidator() {
            return mockFactory.Mock(UserValidator)
        }

    }
}