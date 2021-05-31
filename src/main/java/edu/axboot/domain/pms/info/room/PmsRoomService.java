package edu.axboot.domain.pms.info.room;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.PmsRoomListResponseDto;
import edu.axboot.controllers.dto.PmsRoomSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PmsRoomService extends BaseService<PmsRoom, Long> {
    private final PmsRoomRepository pmsRoomRepository;

    public List<PmsRoomListResponseDto> findByL(String roomTypCd) {

        BooleanBuilder builder = new BooleanBuilder();
        if(isNotEmpty(roomTypCd)){
            builder.and(qPmsRoom.roomTypCd.eq(roomTypCd));
        }
        List<PmsRoom> entitis = select()
                .from(qPmsRoom)
                .where(builder)
                .orderBy(qPmsRoom.roomNum.asc())
                .fetch();

        return entitis.stream()
                .map(PmsRoomListResponseDto::new)//
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Long> save(List<PmsRoomSaveRequestDto> dtos) {
        List<Long> ids = new ArrayList<Long>();
        for (PmsRoomSaveRequestDto dto: dtos) {
            if (dto.is__deleted__()) {
                pmsRoomRepository.delete(dto.getId());
                ids.add(dto.getId());
            } else {
                ids.add(pmsRoomRepository.save(dto.toEntity()).getId());
            }
        }
        return ids;
    }
    }

/* @Inject
    public PmsRoomService(PmsRoomRepository pmsRoomRepository) {
        super(pmsRoomRepository);
        this.pmsRoomRepository = pmsRoomRepository;
    }*/

/*
    public List<PmsRoom> getUsingQuery(RequestParams<PmsRoom> requestParams) {
        String roomNum = requestParams.getString("roomNum", "");
        String roomTypCd = requestParams.getString("roomTypCd", "");
        String dndYn = requestParams.getString("dndYn", "");
        String ebYn = requestParams.getString("ebYn", "");
        String roomSttusCd = requestParams.getString("roomSttusCd", "");
        String clnSttusCd = requestParams.getString("clnSttusCd", "");
        String svcSttusCd = requestParams.getString("svcSttusCd", "");

        BooleanBuilder builder = new BooleanBuilder();
        if (isNotEmpty(roomNum)) {
            builder.and(qPmsRoom.roomNum.contains(roomNum));
        }
        if (isNotEmpty(roomTypCd)) {
            builder.and(qPmsRoom.roomTypCd.contains(roomTypCd));
        }
        if (isNotEmpty(dndYn)) {
            builder.and(qPmsRoom.dndYn.contains(dndYn));
        }
        if (isNotEmpty(ebYn)) {
            builder.and(qPmsRoom.ebYn.contains(ebYn));
        }
        if (isNotEmpty(roomSttusCd)) {
            builder.and(qPmsRoom.roomSttusCd.contains(roomSttusCd));
        }
        if (isNotEmpty(clnSttusCd)) {
            builder.and(qPmsRoom.clnSttusCd.contains(clnSttusCd));
        }
        if (isNotEmpty(svcSttusCd)) {
            builder.and(qPmsRoom.svcSttusCd.contains(svcSttusCd));
        }
        List<PmsRoom> pmsRoomList = select()
                .from(qPmsRoom)
                .where(builder)
                .orderBy(qPmsRoom.roomNum.asc())
                .fetch();
        return pmsRoomList;
    }


    @Transactional
    public void saveUsingQuery(List<PmsRoom> request) {
        for (PmsRoom pmsRoom : request) {
            if (pmsRoom.isCreated()) {
                save(pmsRoom);
            } else if (pmsRoom.isModified()) {
                update(qPmsRoom)
                        .set(qPmsRoom.roomNum, pmsRoom.getRoomNum())
                        .set(qPmsRoom.roomTypCd, pmsRoom.getRoomTypCd())
                        .set(qPmsRoom.dndYn, pmsRoom.getDndYn())
                        .set(qPmsRoom.ebYn, pmsRoom.getEbYn())
                        .set(qPmsRoom.roomSttusCd, pmsRoom.getRoomSttusCd())
                        .set(qPmsRoom.clnSttusCd, pmsRoom.getClnSttusCd())
                        .set(qPmsRoom.svcSttusCd, pmsRoom.getSvcSttusCd())
                        .where(qPmsRoom.id.eq(pmsRoom.getId()))
                        .execute();
            } else if (pmsRoom.isDeleted()) {
                delete(qPmsRoom)
                        .where(qPmsRoom.id.eq(pmsRoom.getId()))
                        .execute();
            }
        }
    }
*/

/*
    @Transactional
    public void saveUsingQueryDsl(PmsRoom entity) {
        if(entity.getId() == null || entity.getId() ==0){
            this.pmsRoomRepository.save(entity);
        }else{
            update(qPmsRoom)
                    .set(qPmsRoom.roomNum, entity.getRoomNum())
                    .set(qPmsRoom.roomTypCd, entity.getRoomTypCd())
                    .set(qPmsRoom.dndYn, entity.getDndYn())
                    .set(qPmsRoom.ebYn, entity.getEbYn())
                    .set(qPmsRoom.roomSttusCd, entity.getRoomSttusCd())
                    .set(qPmsRoom.clnSttusCd, entity.getClnSttusCd())
                    .set(qPmsRoom.svcSttusCd, entity.getSvcSttusCd())
                    .where(qPmsRoom.id.eq(entity.getId()))
                    .execute();
        }
    }

    @Transactional
    public void deleteUsingQueryDsl(List<Long> ids) {
        for(Long id: ids){
            deleteUsingQueryDsl(ids);
        }
    }
    @Transactional
    public void deleteUsingQueryDsl(Long id) {
    delete(qPmsRoom).where(qPmsRoom.id.eq(id)).execute();
    }
}*/




