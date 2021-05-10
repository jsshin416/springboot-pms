package edu.axboot.domain.pms.room;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class PmsRoomService extends BaseService<PmsRoom, Long> {
    private PmsRoomRepository pmsRoomRepository;

    @Autowired
    @Inject
    public PmsRoomService(PmsRoomRepository pmsRoomRepository) {
        super(pmsRoomRepository);
        this.pmsRoomRepository = pmsRoomRepository;
    }

    public List<PmsRoom> gets(RequestParams<PmsRoom> requestParams) {
        return findAll();
    }

    public List<PmsRoom> getUsingQuery(RequestParams<PmsRoom> requestParams) {
        String roomNum = requestParams.getString("roomNum", "");
        String roomTypCd = requestParams.getString("roomTypCd", "");
        String dndYn = requestParams.getString("dndYn", "");
        String ebYn = requestParams.getString("ebYn", "");
        String roomSttusCd = requestParams.getString("roomSttusCd", "");
        String clnSttusCd = requestParams.getString("clnSttusCd", "");
        String svcSttusCd  = requestParams.getString("svcSttusCd", "");

        BooleanBuilder builder = new BooleanBuilder();
        if(isNotEmpty(roomNum)){
            builder.and(qPmsRoom.roomNum.contains(roomNum));
        }
        if(isNotEmpty(roomTypCd)){
            builder.and(qPmsRoom.roomTypCd.contains(roomTypCd));
        }
        if(isNotEmpty(dndYn)){
            builder.and(qPmsRoom.dndYn.contains(dndYn));
        }
        if(isNotEmpty(ebYn)){
            builder.and(qPmsRoom.ebYn.contains(ebYn));
        }
        if(isNotEmpty(roomSttusCd)){
            builder.and(qPmsRoom.roomSttusCd.contains(roomSttusCd));
        }
        if(isNotEmpty(clnSttusCd)){
            builder.and(qPmsRoom.clnSttusCd.contains(clnSttusCd));
        }
        if(isNotEmpty(svcSttusCd)){
            builder.and(qPmsRoom.svcSttusCd.contains(svcSttusCd));
        }
        List<PmsRoom> pmsRoomList = select()
                .from(qPmsRoom)
                .where(builder)
                .orderBy(qPmsRoom.roomNum.asc())
                .fetch();
        return pmsRoomList;
    }


    public void saveUsingQuery(List<PmsRoom> request) {
        for(PmsRoom pmsRoom : request){
            if(pmsRoom.is__created__()){
                save(pmsRoom);
            }else if(pmsRoom.is__modified__()){
                update(qPmsRoom)
                        .set(qPmsRoom.roomNum, pmsRoom.getRoomNum())
                        .set(qPmsRoom.roomTypCd, pmsRoom.getRoomTypCd())
                        .set(qPmsRoom.dndYn, pmsRoom.getDndYn())
                        .set(qPmsRoom.ebYn, pmsRoom.getEbYn())
                        .set(qPmsRoom.roomSttusCd, pmsRoom.getRoomSttusCd())
                        .set(qPmsRoom.clnSttusCd, pmsRoom.getClnSttusCd())
                        .set(qPmsRoom.svcSttusCd, pmsRoom.getSvcSttusCd())
                        .execute();
            }else if(pmsRoom.is__deleted__()){
                delete(qPmsRoom)
                    .where(qPmsRoom.id.eq(pmsRoom.getId()))
                    .execute();
            }
        }
    }
}