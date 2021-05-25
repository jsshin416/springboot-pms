package edu.axboot.domain.pms.book.booking;


import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.BookingSaveRequestDto;
import edu.axboot.controllers.dto.MemoSaveRequestDto;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookingTest {
    @Autowired
    private  BookingService bookingService;
    public static long ids =0;
    @Test
    public void test1_예약등록_저장하기() {
        //given
      BookingSaveRequestDto dtos = BookingSaveRequestDto.builder()
              .arrDt("2021-05-20")
              .depDt("2021-05-21")
              .nightCnt(1)
              .roomTypCd("SB")
              .adultCnt(2)
              .chldCnt(0)
              .saleTypCd("01")
              .srcCd("CMS")
              .salePrc(new BigDecimal(50000))
              .advnYn("N")
              .guestId(1L)
              .guestNm("김여빈")
              .guestNmEng("kim")
              .guestTel("010-1111-2222")
              .email("kim@naver.com")
                .build();
        //when
        ids = bookingService.save(dtos);
        //then
        assertTrue(ids >0);
    }

    @Test
    public void test2_예약_등록하기_기존투숙객() {
        //given
        BookingSaveRequestDto saveDto = BookingSaveRequestDto.builder()
                .arrDt("2021-05-20")
              .depDt("2021-05-21")
              .nightCnt(1)
              .roomTypCd("SB")
              .adultCnt(2)
              .chldCnt(0)
              .saleTypCd("01")
              .srcCd("CMS")
              .salePrc(new BigDecimal(50000))
              .advnYn("N")
              .guestId(1L)
              .guestNm("김여빈")
              .guestNmEng("kim")
              .guestTel("010-1111-2222")
              .email("kim@naver.com")
                .build();

        //when
        ids = bookingService.save(saveDto);

        //then
        assertTrue(ids > 0);
    }

    @Test
    public void test3_예약_숙박메모_등록하기() {
        //given
        List<MemoSaveRequestDto> memos = new ArrayList<MemoSaveRequestDto>();
        memos.add(MemoSaveRequestDto.builder()
                .memoCn("테스트입력")
                .__created__(true)
                .__modified__(false)
                .__deleted__(false)
                .build());

        BookingSaveRequestDto saveDto = BookingSaveRequestDto.builder()
                .arrDt("2021-05-20")
                .depDt("2021-05-21")
                .nightCnt(1)
                .roomTypCd("SB")
                .adultCnt(2)
                .chldCnt(0)
                .saleTypCd("01")
                .srcCd("CMS")
                .salePrc(new BigDecimal(50000))
                .advnYn("N")
                .guestId(1L)
                .guestNm("김여빈")
                .guestNmEng("kim")
                .guestTel("010-1111-2222")
                .email("kim@naver.com")
                .memoList(memos)
                .build();

        //when
        ids = bookingService.save(saveDto);

        //then
        assertTrue(ids > 0);
    }



}
