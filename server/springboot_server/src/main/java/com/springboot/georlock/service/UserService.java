package com.springboot.georlock.service;


import com.springboot.georlock.entity.User;
import com.springboot.georlock.repository.UserRepository;
import com.springboot.georlock.request.UserUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

//    @Autowired
//    AccessMapper accessMapper;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public List<User> getAll() throws Exception {
//        return accessMapper.getAll();
//    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

//    public void accessUpdate(User login) throws Exception {
//        accessMapper.update(login);
//    }

    @Transactional
    public void update(User user, UserUpdateRequest request) {
        user.setInTime(request.intTime());
        user.setOutTime(request.outTime());
        userRepository.save(user);
    }

//    @Transactional
//    public void accessDelete(String empNo) throws Exception {
//        accessMapper.delete(empNo);
//    }

//    public List<User> accessSearch(String textSearch) throws Exception {
//        String Search = "%" + textSearch + "%";
//        return accessMapper.Search(Search);
//    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}
