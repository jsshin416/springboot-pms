package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.info.guest.Guest;
import lombok.Getter;

@Getter
public class GuestListResponseDto {
    private Long id;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String langCd;
    private String rmk;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    public GuestListResponseDto(Guest entity){
        this.id =entity.getId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();
        this.langCd = entity.getLangCd();
        this.rmk = entity.getRmk();
        this.__created__ = false;
        this.__modified__ = false;
        this.__deleted__ = false;
    }

}
