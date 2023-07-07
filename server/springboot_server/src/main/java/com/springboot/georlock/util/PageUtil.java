package com.springboot.georlock.util;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public class PageUtil {
    public static void set(
            Pageable pageable, ModelAndView mav, int boardPageTotalPages
    ) {
        int currentPage = pageable.getPageNumber();

        int basePage = 10;
        int startPage = (currentPage / basePage) * basePage + 1;
        int endPage = startPage + basePage - 1;

        if (endPage > boardPageTotalPages) {
            endPage = boardPageTotalPages;
        }

        if (startPage > endPage) {
            startPage = endPage;
        }

        int currentDivision = currentPage / basePage; // 49 / 10 = 4
        int totalDivision = boardPageTotalPages / basePage; // 55 / 10 = 5

        if (currentDivision == 0) {
            mav.addObject("prev" , false);
        } else {
            mav.addObject("prev" , true);
        }

        if (currentDivision < totalDivision) {
            mav.addObject("next" , true);
        } else {
            mav.addObject("next" , false);
        }

        if (basePage > currentPage) {
            startPage = 1;
        }

        mav.addObject("basePage", basePage);
        mav.addObject("startPage", startPage);
        mav.addObject("endPage", endPage);

    }

}
