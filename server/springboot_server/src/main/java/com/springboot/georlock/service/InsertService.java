package com.springboot.georlock.service;

import com.springboot.georlock.entity.User;
import com.springboot.georlock.enums.RoleType;
import com.springboot.georlock.enums.UserStatus;
import com.springboot.georlock.repository.UserRepository;
import com.springboot.georlock.request.UserSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsertService {

    private final UserRepository userRepository;

    private final String NFCPATTERN = "0000FF078069FFFFFFFFFFFF";

    public InsertService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String accessInsert(UserSaveRequest request) throws Exception {
        User user = new User(
                null,
                request.empNo(),
                request.username(),
                null,
                null,
                null,
                RoleType.USER,
                null,
                null,
                UserStatus.WAIT
        );
        user.setPassword(user.getEmpNo());
        user.setNfc(user.getEmpNo() + NFCPATTERN);
        userRepository.save(user);
        return user.getNfc();
    }
//
//    public List<Login> empList() throws Exception {
//        return insertMapper.emplist();
//    }
//
//    public List<Login> empSearch(String textSearch) throws Exception {
//        String Search = "%" + textSearch + "%";
//        return insertMapper.empSearch(Search);
//    }

}
