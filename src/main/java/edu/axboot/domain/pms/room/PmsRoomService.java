package edu.axboot.domain.pms.room;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.PmsRoomListDto;
import edu.axboot.controllers.dto.PmsRoomSaveDto;
import edu.axboot.controllers.dto.PmsRoomUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PmsRoomService extends BaseService<PmsRoom, Long> {
    private final PmsRoomRepository pmsRoomRepository;

  /*  @Inject
    public PmsRoomService(PmsRoomRepository pmsRoomRepository) {
        super(pmsRoomRepository);
        this.pmsRoomRepository = pmsRoomRepository;
    }*/

    @Transactional
    public Long saveDto(PmsRoomSaveDto requestDto) {
        return pmsRoomRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PmsRoomUpdateDto requestDto) {
        PmsRoom pmsRoom = pmsRoomRepository.findOne(id);

        if (pmsRoom == null) {
            throw new IllegalArgumentException("해당 거래처가 없습니다. id=" + id);
        }
        pmsRoom.update(requestDto.getRoomNum(), requestDto.getRoomTypCd(),
                requestDto.getDndYn(), requestDto.getEbYn(),requestDto.getRoomSttusCd(),
                requestDto.getClnSttusd(),requestDto.getSvcSttusCd());
        return id;
    }

    public List<PmsRoomListDto> findByAll(String roomNum, String roomTypCd, String dndYn,
                                        String ebYn, String roomSttusCd,
                                        String clnSttusCd, String svcSttusCd) {

        BooleanBuilder builder = new BooleanBuilder();
        if(isNotEmpty(roomNum)){
            builder.and(qPmsRoom.roomNum.like("%" + roomNum +"%"));
        }
        if(isNotEmpty(roomTypCd)){
            builder.and(qPmsRoom.roomTypCd.like("%" + roomTypCd +"%"));
        }
        if(isNotEmpty(dndYn)){
            builder.and(qPmsRoom.dndYn.like("%" + dndYn +"%"));
        }
        if(isNotEmpty(ebYn)){
            builder.and(qPmsRoom.ebYn.like("%" + ebYn +"%"));
        }
        if(isNotEmpty(roomSttusCd)){
            builder.and(qPmsRoom.roomSttusCd.like("%" + roomSttusCd +"%"));
        }
        if(isNotEmpty(clnSttusCd)){
            builder.and(qPmsRoom.clnSttusCd.like("%" + clnSttusCd +"%"));
        }
        if(isNotEmpty(svcSttusCd)){
            builder.and(qPmsRoom.svcSttusCd.like("%" + svcSttusCd +"%"));
        }
        List<PmsRoom> entitis = select()
                .from(qPmsRoom)
                .where(builder)
                .orderBy(qPmsRoom.roomNum.asc())
                .fetch();

        return entitis.stream()
                .map(PmsRoomListDto::new)//
                .collect(Collectors.toList());
    }
    }


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




