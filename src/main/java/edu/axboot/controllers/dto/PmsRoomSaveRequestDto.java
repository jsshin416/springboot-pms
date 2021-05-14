package edu.axboot.controllers.dto;

import com.chequer.axboot.core.domain.base.AXBootCrudModel;
import edu.axboot.domain.pms.info.room.PmsRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PmsRoomSaveRequestDto {
    private Long id;
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;



    @Builder
    public PmsRoomSaveRequestDto(Long id, String roomNum, String roomTypCd, String dndYn,
                                 String ebYn, String roomSttusCd,
                                 String clnSttusCd, String svcSttusCd,
                                 boolean __created__,boolean __modified__,boolean __deleted__){
        this.id = id;
        this.roomNum = roomNum;
        this.roomTypCd = roomTypCd;
        this.dndYn = dndYn;
        this.ebYn =ebYn;
        this.roomSttusCd = roomSttusCd;
        this.clnSttusCd = clnSttusCd;
        this. svcSttusCd =svcSttusCd;
        this.__created__=__created__;
        this.__modified__=__modified__;
        this.__deleted__=__deleted__;

    }

    public PmsRoom toEntity() {
        return PmsRoom.builder()
                .id(id)
                .roomNum(roomNum)
                .roomTypCd(roomTypCd)
                .dndYn(dndYn)
                .ebYn(ebYn)
                .roomSttusCd(roomSttusCd)
                .clnSttusCd(clnSttusCd)
                .svcSttusCd(svcSttusCd)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
