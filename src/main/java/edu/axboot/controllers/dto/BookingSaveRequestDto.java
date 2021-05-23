package edu.axboot.controllers.dto;

import com.querydsl.core.types.Projections;
import edu.axboot.domain.pms.book.booking.Booking;
import edu.axboot.domain.pms.book.booking.BookingRepository;
import edu.axboot.domain.pms.book.booking.BookingService;
import edu.axboot.domain.pms.book.memo.Memo;
import edu.axboot.domain.pms.info.guest.Guest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;

@Getter
@NoArgsConstructor
public class BookingSaveRequestDto {
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
    private List<Memo> memoList = new ArrayList<Memo>();
    //private List<Guest> guestList;

    @Builder
    public BookingSaveRequestDto(Long id, String rsvDt, Integer sno, String rsvNum,
                                 Long guestId, String guestNm,
                                 String guestNmEng, String guestTel,
                                 String email, String langCd, String arrDt, String arrTime,
                                 String depDt, String depTime, Integer nightCnt, String roomTypCd,
                                 String roomNum, Integer adultCnt, Integer chldCnt, String saleTypCd,
                               String sttusCd,String srcCd, String brth, String gender, String payCd, String advnYn,
                                 BigDecimal salePrc, BigDecimal svcPrc,
                                 boolean __created__, boolean __modified__, boolean __deleted__,
                                 List<Memo>memoList) {



        this.id = id;
        this.rsvDt = rsvDt;
        this.sno = sno;
        this.rsvNum = rsvNum;
        this.guestId = guestId;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.langCd = langCd;
        this.arrDt = arrDt;
        this.arrTime = arrTime;
        this.depDt = depDt;
        this.depTime = depTime;
        this.nightCnt = nightCnt;
        this.roomTypCd = roomTypCd;
        this.roomNum = roomNum;
        this.adultCnt = adultCnt;
        this.chldCnt = chldCnt;
        this.saleTypCd = saleTypCd;
        this.sttusCd = sttusCd;
        this.srcCd = srcCd;
        this.brth = brth;
        this.gender = gender;
        this.payCd = payCd;
        this.advnYn = advnYn;
        this.salePrc = salePrc;
        this.svcPrc = svcPrc;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
        this.memoList = memoList;
        //this.guestList =guestList;
    }

    public Booking toEntity() {
        return Booking.builder()
                .id(id)
                .rsvDt(rsvDt)
                .sno(sno)
                .rsvNum(rsvNum)
                .guestId(guestId)
                .guestNm(guestNm)
                .guestNmEng(guestNmEng)
                .guestTel(guestTel)
                .email(email)
                .langCd(langCd)
                .aarDt(arrDt)
                .arrTime(arrTime)
                .depDt(depDt)
                .depTime(depTime)
                .nightCnt(nightCnt)
                .roomTypCd(roomTypCd)
                .roomNum(roomNum)
                .adultCnt(adultCnt)
                .chldCnt(chldCnt)
                .saleTypCd(saleTypCd)
                .sttusCd(sttusCd)
                .srcCd(srcCd)
                .brth(brth)
                .gender(gender)
                .payCd(payCd)
                .advnYn(advnYn)
                .salePrc(salePrc)
                .svcPrc(svcPrc)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .memoList(memoList)
                .build();


    }



}

