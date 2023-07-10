package com.springboot.georlock.controller;


import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.entity.EnterUser;
import com.springboot.georlock.service.EnterUserService;
import com.springboot.georlock.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/record")
public class RecordController {
    private final EnterUserService recordService;

    public RecordController(EnterUserService recordService) {
        this.recordService = recordService;
    }

    @GetMapping({"/", ""})  //출입 기록 페이지 이동
    public ModelAndView record(
            @PageableDefault(sort = "id" ,direction = Sort.Direction.DESC, size = 10)
            Pageable pageable
    ) throws Exception {
        ModelAndView mav = new ModelAndView("record");
        Page<EnterUser> enterUserPage = recordService.getEnterEmp(pageable);   //출입 기록 조회

        PageUtil.set(pageable, mav, enterUserPage.getTotalPages());

        mav.addObject("recordList", enterUserPage);
        return mav;
    }

//    @RequestMapping("/recordSearch")  //출입 기록 페이지 검색 기능
//    public ModelAndView recordSearch(Dates dates) throws Exception {
//        ModelAndView mav = new ModelAndView("record");
//        List<Enteremp> list = recordService.getRecordSearch(dates);  //출입 기록 검색
//        mav.addObject("recordList", list);
//        return mav;
//    }


}
