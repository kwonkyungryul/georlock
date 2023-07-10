package com.springboot.georlock.controller;

import com.springboot.georlock.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/save")
public class InsertController {

    private final UserService userService;

    public InsertController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    public ModelAndView saveForm() {
        ModelAndView mav = new ModelAndView("save");
        List<Integer> list = Arrays.asList(1, 2);



        mav.addObject("empUserList", list);  //등록 되지 않은 회원 정보 조회
        return mav;
    }

//    @RequestMapping("/empSearch")      //등록 페이지 검색 기능
//    public ModelAndView empSearch(@RequestParam String textSearch) throws Exception {
//        ModelAndView mav = new ModelAndView("insert");
//        mav.addObject("empuser", insertService.empSearch(textSearch)); //등록 되지 않은 회원 정보 검색
//        return mav;
//    }
//
//    @RequestMapping("/accessInsert")      //등록 및 nfc 값 조회
//    public String accessInsert(Login login) throws Exception {
//        String nfc = insertService.accessInsert(login); //등록 및 nfc 값 조회
//        setNfc(nfc);   //nfc값 셋팅
//        return "redirect:access";
//    }
//
//    //nfc 등록을 위한 메소드
//    public void setNfc(String nfc) throws Exception {
//        loginService.setNfc(nfc); //nfc값 셋팅
//    }
}
