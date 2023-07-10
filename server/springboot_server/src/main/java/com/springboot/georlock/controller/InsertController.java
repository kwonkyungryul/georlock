package com.springboot.georlock.controller;

import com.springboot.georlock.request.UserSaveRequest;
import com.springboot.georlock.service.InsertService;
import com.springboot.georlock.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/save")
public class InsertController {

    private final UserService userService;

    private final InsertService insertService;

    public InsertController(UserService userService, InsertService insertService) {
        this.userService = userService;
        this.insertService = insertService;
    }

//    @RequestMapping({"", "/"})
//    public ModelAndView saveForm(
//            @PageableDefault(sort = "id" , direction = Sort.Direction.DESC, size = 10)
//            Pageable pageable
//    ) {
//        ModelAndView mav = new ModelAndView("save");
//
//        Page<User> userPage = userService.findAll(pageable);
//        PageUtil.set(pageable, mav, userPage.getTotalPages());
//
//        mav.addObject("empUserList", userPage);  //등록 되지 않은 회원 정보 조회
//        return mav;
//    }

    @RequestMapping({"", "/"})
    public ModelAndView saveForm(
            @PageableDefault(sort = "id" , direction = Sort.Direction.DESC, size = 10)
            Pageable pageable
    ) {
        ModelAndView mav = new ModelAndView("insert");
        return mav;
    }

//    @RequestMapping("/empSearch")      //등록 페이지 검색 기능
//    public ModelAndView empSearch(@RequestParam String textSearch) throws Exception {
//        ModelAndView mav = new ModelAndView("insert");
//        mav.addObject("empuser", insertService.empSearch(textSearch)); //등록 되지 않은 회원 정보 검색
//        return mav;
//    }

    @GetMapping("/insert")
    public ModelAndView insertForm() {
        ModelAndView mav = new ModelAndView("insert");
        return mav;
    }

    @PostMapping("/insert")      //등록 및 nfc 값 조회
    public String accessInsert(UserSaveRequest request) throws Exception {
        String nfc = insertService.accessInsert(request); //등록 및 nfc 값 조회

        // TODO NFC 값 세팅 해야 함
//        setNfc(nfc);   //nfc값 셋팅
        return "redirect:/access";
    }
//
    //nfc 등록을 위한 메소드
//    public void setNfc(String nfc) throws Exception {
//        loginService.setNfc(nfc); //nfc값 셋팅
//    }
}
