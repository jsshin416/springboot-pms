package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.book.booking.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class BookingListResponseDto {
    private Long id;
    private String rsvNum;
    private String rsvDt;
    private String guestNm;
    private String arrDt;
    private String depDt;
    private Integer nightCnt;
    private String roomTypCd;
    private String roomNum;
    private String srcCd;
    private String saleTypCd;
    private String sttusCd;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;


    public BookingListResponseDto(Booking entity){
        this.id = entity.getId();
        this.rsvNum = entity.getRsvNum();
        this.rsvDt = entity.getRsvDt();
        this.guestNm  = entity.getGuestNm();
        this.arrDt =entity.getArrDt();
        this.depDt = entity.getDepDt();
        this.nightCnt = entity.getNightCnt();
        this.roomTypCd =entity.getRoomTypCd();
        this.roomNum =entity.getRoomNum();
        this.saleTypCd = entity.getSaleTypCd();
        this.sttusCd = entity.getSttusCd();
        this.srcCd = entity.getSrcCd();
        this.__created__ =false;
        this.__modified__ =false;
        this.__deleted__ =false;

    }

}
