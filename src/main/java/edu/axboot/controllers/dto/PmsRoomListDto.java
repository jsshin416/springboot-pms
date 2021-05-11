package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.room.PmsRoom;
import lombok.Getter;

@Getter
public class PmsRoomListDto {
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusd;
    private String svcSttusCd;

    public PmsRoomListDto(PmsRoom entity){
        this.roomNum = entity.getRoomNum();
        this.roomTypCd = entity.getRoomTypCd();
        this.dndYn = entity.getDndYn();
        this.ebYn =entity.getEbYn();
        this.roomSttusCd = entity.getRoomSttusCd();
        this.clnSttusd = entity.getClnSttusCd();
        this. svcSttusCd =entity.getSvcSttusCd();
    }

}
