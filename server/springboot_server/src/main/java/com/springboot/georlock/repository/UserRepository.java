package com.springboot.georlock.repository;

import com.springboot.georlock.entity.User;
import com.springboot.georlock.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByEmpNoContainingOrUsernameContainingAndStatus(Pageable pageable, String empNo, String username, UserStatus status);

    Page<User> findAllByStatus(Pageable pageable, UserStatus status);
}
