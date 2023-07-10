package com.springboot.georlock.repository;

import com.springboot.georlock.entity.User;
import com.springboot.georlock.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByEmpNoContainingOrUsernameContainingAndStatusNot(Pageable pageable, String empNo, String username, UserStatus status);

    Page<User> findAllByStatusNot(Pageable pageable, UserStatus status);
}
