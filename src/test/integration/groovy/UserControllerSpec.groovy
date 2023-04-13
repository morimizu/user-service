import com.benjaminrperry.userservice.controller.UserController
import com.benjaminrperry.userservice.dto.CreateUserDTO
import com.benjaminrperry.userservice.repository.UserRepository
import com.benjaminrperry.userservice.service.UserService
import com.benjaminrperry.userservice.service.UserServiceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = [TestConfig.class])
@WebMvcTest([UserController.class])
class UserControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
//    @Named("mockUserRepository")
    UserRepository userRepository

    @Shared
    ObjectMapper objectMapper = new ObjectMapper()

    @Shared
    Random random = new Random()

    def 'register user'() {
        given:
        def dto = randomUserDto()
        userRepository.findByUsername(_) >> null
        userRepository.findByEmail(_) >> null

        when:
        def result = mockMvc.perform(post("http://localhost:8080/users", dto))

        then:
        result.andExpect(status().isOk())
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
        objectMapper.writeValueAsString(content)
    }

    @TestConfiguration
    static class TestConfig {
        def mockFactory = new DetachedMockFactory()

        @Bean
        UserService userService() {
            return new UserServiceImpl(mockUserRepository())
        }

        @Bean
        UserRepository mockUserRepository() {
            return mockFactory.Mock(UserRepository)
        }

        @Bean
        UserController userController() {
            return new UserController(userService())
        }
    }
}