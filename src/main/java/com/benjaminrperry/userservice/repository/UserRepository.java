package com.benjaminrperry.userservice.repository;

import com.benjaminrperry.userservice.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserJpa,Long> {
}
