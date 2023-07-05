package com.springboot.georlock.controller;


import com.springboot.georlock.entity.User;
import com.springboot.georlock.exception.CustomException;
import com.springboot.georlock.request.UserUpdateRequest;
import com.springboot.georlock.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
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
    public ModelAndView access() {
        ModelAndView mav = new ModelAndView("access");
        List<User> userlist = userService.findAll();      // 웹에 나타낼 회원 정보(출입 권한) 조회
        mav.addObject("userlist", userlist);
        return mav;
    }

// TODO 주석

//    @RequestMapping("/search")      // 회원 정보(출입 권한 관리) 검색 기능(사번 or 이름)
//    public ModelAndView accessSearch(String textSearch) throws Exception {
//        ModelAndView mav = new ModelAndView("access");
//        List<User> userlist = userService.accessSearch(textSearch); // 웹에 나타낼 검색된 회원 정보(출입 권한) 조회
//        mav.addObject("userlist", userlist);
//        return mav;
//    }

// TODO 주석

//    @RequestMapping("/delete")      //회원 정보(출입 권한) 삭제 기능
//    public String accessDelete(@RequestParam String empNo) throws Exception {
//        userService.accessDelete(empNo);  //회원 정보 삭제
//        return "redirect:access";
//    }


    @GetMapping("/modify")     //출입 시간 수정 페이지 이동
    public ModelAndView accessModify(@RequestParam String empNo, @RequestParam String username) throws Exception {
        ModelAndView mav = new ModelAndView("modify");
        // TODO UserId도 model에 주입해야 함.
        mav.addObject("empNo", empNo);
        mav.addObject("username", username);
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
