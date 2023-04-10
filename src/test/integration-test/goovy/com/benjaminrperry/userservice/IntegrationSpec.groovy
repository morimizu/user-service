package com.benjaminrperry.userservice

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration(classes = [UserServiceApplication.class])
class IntegrationSpec extends Specification {

}
