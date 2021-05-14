package edu.axboot.domain.pms.book.booking;


import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.BookingSaveRequestDto;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingTest {
    @Autowired
    private  BookingService bookingService;

    public static List<Long> ids;
    @Test
    public void test1_예약등록_저장하기() {
        //given
        List<BookingSaveRequestDto> dtos = new ArrayList<BookingSaveRequestDto>();
        dtos.add(BookingSaveRequestDto.builder()
                .id(null)
                .arrDt("2021051")
                .nightCnt(2)
                .depDt("20210515")
                .roomTypCd("EE")
                .saleTypCd("온라인")
                .sttusCd("전화")
                .srcCd("소스")
                .sno(1111)
                .rsvNum("20210514001")
                .roomNum("101")

                .__created__(true)
                .__modified__(false)
                .__deleted__(false)
                .build());
        //when
        ids = bookingService.save(dtos);
        //then
        assertTrue(ids.size() == dtos.size());
    }



}
