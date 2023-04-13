import com.benjaminrperry.userservice.UserServiceApplication
import com.benjaminrperry.userservice.dto.CreateUserDTO
import com.benjaminrperry.userservice.service.UserService
import jakarta.validation.ConstraintDeclarationException
import jakarta.validation.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [UserServiceApplication.class])
class UserServiceSpec extends Specification {

    @Autowired
    UserService userService

    Random random

    def setup(){
        random = new Random()
    }

    def createUserDto(username = "testUser", email = "test@test.com", password = "password") {
        CreateUserDTO.builder()
                .username(username)
                .email(email)
                .password(password)
                .build()
    }

//    @Unroll
    def 'create passes'() {
        given:
        def testUser = "testUser${random.nextInt()}"
        def testEmail = "${testUser}@test.com"
        def dto = createUserDto(testUser.toString(),testEmail.toString())

        when:
        def result = userService.registerNewUser(dto)

        then:
        noExceptionThrown()

//        where:
//        test                           | exists | passes
//        'non existing username passes' | false  | true
//        'existing username fails'      | true   | false
    }

//    @Unroll
    def 'trigger validation exception - #test'() {
        given:
        def dto = createUserDto()

        when:
        def result = userService.registerNewUser(dto)

        then:
        thrown(ConstraintViolationException)

//        where:
//        test                           | exists | passes
//        'non existing username passes' | false  | true
//        'existing username fails'      | true   | false
    }


}
