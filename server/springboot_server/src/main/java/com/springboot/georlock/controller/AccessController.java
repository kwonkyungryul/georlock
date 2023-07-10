package com.springboot.georlock.controller;


import com.springboot.georlock.entity.User;
import com.springboot.georlock.exception.CustomException;
import com.springboot.georlock.request.UserUpdateRequest;
import com.springboot.georlock.service.UserService;
import com.springboot.georlock.util.PageUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping("/access")
public class AccessController {
    //출입 권한 컨트롤러

    private final UserService userService;

    public AccessController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", ""})      //회원 정보(출입 권한) 관리 페이지 이동
    public ModelAndView access(
            @PageableDefault(sort = "id" , direction = Sort.Direction.DESC, size = 10)
            Pageable pageable,
            @RequestParam(defaultValue = "")
            String textSearch
    ) {
        ModelAndView mav = new ModelAndView("access");
        Page<User> userPage = null;
        if (!textSearch.equals("")) {
            userPage = userService.search(pageable, textSearch);
        } else {
            userPage = userService.findAll(pageable);      // 웹에 나타낼 회원 정보(출입 권한) 조회
        }
        mav.addObject("userPage", userPage);
        mav.addObject("textSearch", textSearch);
        PageUtil.set(pageable, mav, userPage.getTotalPages());

        return mav;
    }

    @DeleteMapping("/delete/{id}")      //회원 정보(출입 권한) 삭제 기능
    public @ResponseBody String accessDelete(@PathVariable Long id) throws Exception {

        Optional<User> optionalUser = userService.getUser(id);  //회원 정보 조회
        if (optionalUser.isEmpty()) {
            throw new CustomException("해당 회원이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        userService.delete(user);  //회원 정보 삭제
        return "삭제가 완료되었습니다.";
    }


    @GetMapping("/modify/{id}")     //출입 시간 수정 페이지 이동
    public ModelAndView accessModify(@PathVariable Long id) throws Exception {
        ModelAndView mav = new ModelAndView("modify");
        Optional<User> optionalUser = userService.getUser(id);
        if (optionalUser.isEmpty()) {
            throw new CustomException("해당 회원이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        mav.addObject("empNo", user.getEmpNo());
        mav.addObject("username", user.getUsername());
        mav.addObject("userId", user.getId());
        return mav;
    }

    @PostMapping("/update/{id}")     //출입 시간 수정 기능
    public String accessUpdate(@Valid UserUpdateRequest request, @PathVariable Long id) throws Exception {
        Optional<User> optionalUser = userService.getUser(id);  //회원 정보 조회
        if (optionalUser.isEmpty()) {
            throw new CustomException("해당 회원이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        userService.update(user, request);  //회원 정보 수정
        return "close";
    }


}
