package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.room.PmsRoom;
import lombok.Getter;

@Getter
public class PmsRoomListRequestDto {
    private Long id;
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusd;
    private String svcSttusCd;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    public PmsRoomListRequestDto(PmsRoom entity){
        this.id = entity.getId();
        this.roomNum = entity.getRoomNum();
        this.roomTypCd = entity.getRoomTypCd();
        this.dndYn = entity.getDndYn();
        this.ebYn =entity.getEbYn();
        this.roomSttusCd = entity.getRoomSttusCd();
        this.clnSttusd = entity.getClnSttusCd();
        this. svcSttusCd =entity.getSvcSttusCd();
        this.__created__ = false;
        this.__modified__ = false;
        this.__deleted__ = false;
    }

}
