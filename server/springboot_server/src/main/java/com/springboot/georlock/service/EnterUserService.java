package com.springboot.georlock.service;

import com.springboot.georlock.dto.Dates;
import com.springboot.georlock.dto.Enteremp;
import com.springboot.georlock.entity.EnterUser;
import com.springboot.georlock.enums.UserStatus;
import com.springboot.georlock.repository.EnterUserRepository;
import com.springboot.georlock.util.DateTimeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterUserService {
//    @Autowired
//    RecordMapper recordMapper;

    private final EnterUserRepository enterUserRepository;

    public EnterUserService(EnterUserRepository enterUserRepository) {
        this.enterUserRepository = enterUserRepository;
    }

    public Page<EnterUser> getEnterEmp(Pageable pageable) throws Exception {

        Page<EnterUser> enterUserPage = enterUserRepository.findAllByUser_Status(pageable, UserStatus.ACTIVE);

        enterUserPage
                .forEach(
                        enterUser -> enterUser.setInTime(
                                DateTimeConverter.stringToLocalDateTime(enterUser.getInTime().toString())
                        )
                );

        return enterUserPage;
    }

//    public List<Enteremp> getRecordSearch(Dates dates) throws Exception {
//        final String DATE_MAX = "999999999999";
//        final String DATE_MIN = "000000000000";
//        final String DATE_START_SECOND = "0000";
//        final String DATE_END_SECOND = "9999";
//        final String DATE_END_SECOND2 = "000000009999";
//
//        dates.setStartDate(dates.getStartDate().replace("-", "") + DATE_START_SECOND);
//        dates.setEndDate(dates.getEndDate().replace("-", "") + DATE_END_SECOND);
//        dates.setTextSearch("%" + dates.getTextSearch() + "%");
//
//        if (dates.getStartDate().equals(DATE_START_SECOND)) {
//            dates.setStartDate(DATE_MIN);
//        }
//
//        if (dates.getEndDate().equals(DATE_END_SECOND) || dates.getEndDate().equals(DATE_END_SECOND2)) {
//            dates.setEndDate(DATE_MAX);
//        }
//
//        List<Enteremp> record = recordMapper.getSearch(dates);
//        Enteremp emp = new Enteremp();
//        for (int i = 0; i < record.size(); i++) {
//            emp = record.get(i);
//            emp.setIntime(emp.getIntime().substring(0, 4) + "-" + emp.getIntime().substring(4, 6) + "-" + emp.getIntime().substring(6, 8) + " " + emp.getIntime().substring(8, 10) + ":" + emp.getIntime().substring(10, 12));
//            record.set(i, emp);
//        }
//
//        return record;
//    }
}
