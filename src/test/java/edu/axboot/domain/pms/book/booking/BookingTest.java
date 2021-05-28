package edu.axboot.domain.pms.book.booking;


import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.*;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(BookingTest.class);
    @Autowired
    private  BookingService bookingService;
    public static long ids =0;
    @Test
    public void test1_예약등록_저장하기() {
        //given
      BookingSaveRequestDto dtos = BookingSaveRequestDto.builder()
              .arrDt("2021-07-28")
              .depDt("2021-07-29")
              .nightCnt(1)
              .roomTypCd("SB")
              .adultCnt(2)
              .chldCnt(0)
              .saleTypCd("01")
              .srcCd("CMS")
              .salePrc(new BigDecimal(50000))
              .advnYn("N")
              .guestId(1L)
              .guestNm("철수")
              .guestNmEng("kim")
              .guestTel("010-0000-2222")
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
    @Test
    public void test4_예약_숙박메모_수정하기() {
        //given
        List<MemoSaveRequestDto> memos = new ArrayList<MemoSaveRequestDto>();
        memos.add(MemoSaveRequestDto.builder()
                .id(1010L)
                .memoCn("테스트 수정4")
                .__created__(false)
                .__modified__(true)
                .__deleted__(false)
                .build());

        BookingSaveRequestDto saveDto = BookingSaveRequestDto.builder()
                .id(1010L)
                .arrDt("2021-07-28")
                .depDt("2021-07-29")
                .nightCnt(1)
                .roomTypCd("SB")
                .adultCnt(2)
                .chldCnt(0)
                .saleTypCd("01")
                .srcCd("CMS")
                .salePrc(new BigDecimal(50000))
                .advnYn("N")
                .guestId(1L)
                .guestNm("철수")
                .guestNmEng("kim")



                .guestTel("010-1111-2222")
                .email("manojun@naver.com")
                .memoList(memos)
                .build();

        //when
        ids = bookingService.save(saveDto);

        //then
        assertTrue(ids > 0);
    }

    @Test
    public void test5_예약정보_불러오기() {
        //given
        Long id = 38L;

        //when
        BookingResponseDto result = bookingService.findById(id);
        logger.info("투숙 메모 건수 ===================== " + result.getMemoList().size());

        //then
        assertTrue(result.getId() == id);
    }

    @Test
    public void test6_예약현황_불러오기() {
        //given
        String filter = "만호";
        String rsvNum = "1800";
        String roomTypCd = "SB";
        String rsvSttDate = "2021-05-18";
        String rsvEndDate = "2021-05-19";
        String arrSttDate = "";
        String arrEndDate = "";
        String depSttDate = "";
        String depEndDate = "";
        List<String> sttusCds = new ArrayList<String>();
        sttusCds.add("RSV");
        sttusCds.add("CNL");

        //when
        List<BookingListResponseDto> result = bookingService.findByL(filter, rsvNum, roomTypCd, rsvSttDate, rsvEndDate, arrSttDate, arrEndDate, depSttDate, depEndDate, sttusCds);
        logger.info("예약 건수 ===================== " + result.size());

        //then
        assertTrue(result.size() >= 0);
    }

    @Test
    public void test7_예약_상태_일괄수정하기() {
        //given
        List<BookingStateRequestDto> reqDtos = new ArrayList<BookingStateRequestDto>();
        reqDtos.add(BookingStateRequestDto.builder()
                .id(38L)
                .sttusCd("CNL")
                .build());

        //when
        int result = bookingService.updateByStatus(reqDtos);

        //then
        assertTrue(result == reqDtos.size());
    }


}
