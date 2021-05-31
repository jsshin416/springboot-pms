package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.book.booking.Booking;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
public class BookingSaveRequestDto {
    private Long id;
    private String arrDt;
    private Integer nightCnt;
    private String depDt;
    private String roomTypCd;
    private Integer adultCnt;
    private Integer chldCnt;

    private Long guestId;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String brth;
    private String gender;

    private String saleTypCd;
    private String srcCd;
    private String payCd;
    private String advnYn;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;
    private String sttusCd;
    private String roomNum;


    private List<MemoSaveRequestDto> memoList = new ArrayList<MemoSaveRequestDto>();


    @Builder
    public BookingSaveRequestDto(Long id, Long guestId, String guestNm,
                                 String guestNmEng, String guestTel,
                                 String email, String langCd, String arrDt,
                                 String depDt, Integer nightCnt, String roomTypCd,
                                 Integer adultCnt, Integer chldCnt, String saleTypCd,
                               String srcCd, String brth, String gender, String payCd, String advnYn,
                                 BigDecimal salePrc, BigDecimal svcPrc,String sttusCd,String roomNum,
                                 List<MemoSaveRequestDto>memoList) {



        this.id = id;
        this.guestId = guestId;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.langCd = langCd;
        this.arrDt = arrDt;
        this.depDt = depDt;
        this.nightCnt = nightCnt;
        this.roomTypCd = roomTypCd;
        this.adultCnt = adultCnt;
        this.chldCnt = chldCnt;
        this.saleTypCd = saleTypCd;
        this.srcCd = srcCd;
        this.brth = brth;
        this.gender = gender;
        this.payCd = payCd;
        this.advnYn = advnYn;
        this.salePrc = salePrc;
        this.svcPrc =svcPrc;
        this.sttusCd = sttusCd;
        this.memoList = memoList;
        this.roomNum = roomNum;


    }

    public Booking toEntity() {
        return Booking.builder()
                .id(id)
                .guestId(guestId)
                .guestNm(guestNm)
                .guestNmEng(guestNmEng)
                .guestTel(guestTel)
                .email(email)
                .langCd(langCd)
                .aarDt(arrDt)
                .depDt(depDt)
                .nightCnt(nightCnt)
                .roomTypCd(roomTypCd)
                .adultCnt(adultCnt)
                .chldCnt(chldCnt)
                .saleTypCd(saleTypCd)
                .srcCd(srcCd)
                .brth(brth)
                .gender(gender)
                .payCd(payCd)
                .advnYn(advnYn)
                .salePrc(salePrc)
                .svcPrc(svcPrc)
                .sttusCd(sttusCd)
                .roomNum(roomNum)
                .build();


    }



}

