package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.book.booking.Booking;
import edu.axboot.domain.pms.book.memo.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BookingResponseDto {
    private Long id;
    private String rsvNum;
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
    private List<MemoResponseDto> memoList = new ArrayList<MemoResponseDto>();

    public BookingResponseDto(Booking entity){
        this.id = entity.getId();
        this.rsvNum = entity.getRsvNum();
        this.guestNm  = entity.getGuestNm();
        this.arrDt =entity.getArrDt();
        this.depDt = entity.getDepDt();
        this.nightCnt = entity.getNightCnt();
        this.roomTypCd =entity.getRoomTypCd();
        this.adultCnt = entity.getAdultCnt();
        this.chldCnt =entity.getChldCnt();
        this.guestId = entity.getGuestId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.langCd = entity.getLangCd();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
        this.saleTypCd = entity.getSaleTypCd();
        this.srcCd = entity.getSrcCd();
        this.payCd = entity.getPayCd();
        this.advnYn =entity.getAdvnYn();
        this.salePrc = entity.getSalePrc();
        this.svcPrc =entity.getSvcPrc();
        this.sttusCd = entity.getSttusCd();
    for(Memo memo: entity.getMemoList()){
        this.memoList.add(new MemoResponseDto(memo));
    }
    }
}
