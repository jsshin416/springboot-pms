package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.room.PmsRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PmsRoomSaveDto {
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusd;
    private String svcSttusCd;


    @Builder
    public PmsRoomSaveDto(String roomNum, String roomTypCd, String dndYn,
                          String ebYn, String roomSttusCd,
                          String clnSttusCd, String svcSttusCd){
        this.roomNum = roomNum;
        this.roomTypCd = roomTypCd;
        this.dndYn = dndYn;
        this.ebYn =ebYn;
        this.roomSttusCd = roomSttusCd;
        this.clnSttusd = clnSttusCd;
        this. svcSttusCd =svcSttusCd;
    }
    public PmsRoom toEntity() {
        return PmsRoom.builder()
                .roomNum(roomNum)
                .roomTypCd(roomTypCd)
                .dndYn(dndYn)
                .ebYn(ebYn)
                .roomSttusCd(roomSttusCd)
                .clnSttusCd(clnSttusd)
                .svcSttusCd(svcSttusCd)
                .build();

    }
}
