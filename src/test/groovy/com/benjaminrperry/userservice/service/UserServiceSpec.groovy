package com.benjaminrperry.userservice.service

import com.benjaminrperry.userservice.repository.UserRepository
import spock.lang.Specification


class UserServiceSpec extends Specification {
    def userRepo = Mock(UserRepository)
    def userService = new UserServiceImpl(userRepo)

    def setup() {
        userRepo.save(_ ) >> _
    }


}