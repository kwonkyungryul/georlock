package com.springboot.georlock.repository;

import com.springboot.georlock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
