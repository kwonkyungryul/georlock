package com.springboot.georlock.service;


import com.springboot.georlock.entity.User;
import com.springboot.georlock.enums.UserStatus;
import com.springboot.georlock.repository.UserRepository;
import com.springboot.georlock.request.UserUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAllByStatusNot(pageable, UserStatus.DELETE);
    }

//    public

    @Transactional
    public void update(User user, UserUpdateRequest request) {
        user.setInTime(request.inTime());
        user.setOutTime(request.outTime());
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Transactional
    public void delete(User user) throws Exception {
        user.setStatus(UserStatus.DELETE);
        userRepository.save(user);
    }

    public Page<User> search(Pageable pageable, String textSearch) {
        return userRepository.findByEmpNoContainingOrUsernameContainingAndStatusNot(pageable, textSearch, textSearch, UserStatus.DELETE);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}
