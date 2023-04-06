package com.benjaminrperry.userservice.mock


import com.benjaminrperry.userservice.entity.UserJpa

import java.time.Instant

class MockUserRepo {

    def list = new ArrayList<UserJpa>()

    UserJpa findByEmail(String email) {
        list.stream().filter { it.email == email }.findFirst().orElse(null)
    }

    UserJpa findByUsername(String username) {
        list.stream().filter { it.username == username }.findFirst().orElse(null)
    }

    UserJpa getOne(Long id) {
        list.stream().filter { it.id == id }.findFirst().orElse(null)
    }


    UserJpa getById(Long id) {
        getOne(id)
    }

    UserJpa getReferenceById(Long id) {
        getOne(id)
    }

    def <S extends UserJpa> S save(S user) {
        def newUser = new UserJpa()
        newUser.id = 1234
        newUser.username = user.username
        newUser.email = user.email
        newUser.creationDate = Instant.now()
        newUser.password = user.password
        list.add(newUser)
        newUser as S
    }

}
