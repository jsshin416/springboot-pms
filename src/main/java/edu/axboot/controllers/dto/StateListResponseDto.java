package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.book.booking.Booking;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class StateListResponseDto {
    private Long id;
    private String rsvDt;
    private Integer sno;
    private String rsvNum;
    private Long guestId;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String arrDt;
    private String arrTime;
    private String depDt;
    private String depTime;
    private Integer nightCnt;
    private String roomTypCd;
    private String roomNum;
    private Integer adultCnt;
    private Integer chldCnt;
    private String saleTypCd;
    private String sttusCd;
    private String srcCd;
    private String brth;
    private String gender;
    private String payCd;
    private String advnYn;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    public StateListResponseDto(Booking entity){
        this.id = entity.getId();
        this.rsvDt= entity.getRsvDt();
        this.sno = entity.getSno();
        this.rsvNum =entity.getRsvNum();
        this.guestId =entity.getGuestId();
        this.guestNm  = entity.getGuestNm();
        this.guestNmEng =entity.getGuestNmEng();
        this.guestTel =entity.getGuestTel();
        this.email = entity.getEmail();
        this.langCd = entity.getLangCd();
        this.arrDt =entity.getArrDt();
        this.arrTime = entity.getArrTime();
        this.depDt = entity.getDepDt();
        this.depTime = entity.getDepTime();
        this.nightCnt = entity.getNightCnt();
        this.roomTypCd =entity.getRoomTypCd();
        this.roomNum =entity.getRoomNum();
        this.adultCnt = entity.getAdultCnt();
        this.chldCnt = entity.getChldCnt();
        this.saleTypCd = entity.getSaleTypCd();
        this.sttusCd = entity.getSttusCd();
        this.srcCd = entity.getSrcCd();
        this.brth = entity.getBrth();
        this.gender= entity.getGender();
        this.payCd =entity.getPayCd();
        this.advnYn = entity.getAdvnYn();
        this.salePrc = entity.getSalePrc();
        this.svcPrc =entity.getSvcPrc();
        this.__created__ =false;
        this.__modified__ =false;
        this.__deleted__ =false;

    }

}
