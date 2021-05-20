package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.book.booking.Booking;
import edu.axboot.domain.pms.info.guest.Guest;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GuestResponseDto {
    private Long id;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String langCd;
    private String rmk;

    private List<Booking> bookingList =new ArrayList<Booking>();


    public GuestResponseDto(Guest entity) {
        this.id = entity.getId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
        this.langCd = entity.getLangCd();
        this.rmk = entity.getRmk();
        this.bookingList.addAll(entity.getBookingList());
    }
}
