package com.springboot.georlock.repository;

import com.springboot.georlock.entity.EnterUser;
import com.springboot.georlock.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterUserRepository extends JpaRepository<EnterUser, Long> {
    Page<EnterUser> findAllByUser_Status(Pageable pageable, UserStatus status);
}

