package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.info.guest.Guest;
import edu.axboot.domain.pms.info.room.PmsRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestSaveRequestDto {
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


    @Builder
    public GuestSaveRequestDto(Long id, String guestNm, String guestNmEng, String guestTel,
                               String email, String brth, String gender, String langCd,
                               String rmk,boolean __created__,boolean __modified__,boolean __deleted__ ) {
        this.id = id;
        this.guestNm = guestNm;
        this.guestNmEng = guestNmEng;
        this.guestTel = guestTel;
        this.email = email;
        this.brth = brth;
        this.gender = gender;
        this.langCd = langCd;
        this.rmk = rmk;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
    }

        public Guest toEntity() {
            return Guest.builder()
                    .id(id)
                    .guestNm(guestNm)
                    .guestNmEng(guestNmEng)
                    .guestTel(guestTel)
                    .email(email)
                    .brth(brth)
                    .gender(gender)
                    .langCd(langCd)
                    .rmk(rmk)
                    .isCreated(__created__)
                    .isModified(__modified__)
                    .isDeleted(__deleted__)
                    .build();
        }
    }



