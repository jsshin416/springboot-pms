package edu.axboot.domain.pms.info.guest;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.GuestListResponseDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.controllers.dto.PmsRoomListResponseDto;
import edu.axboot.domain.pms.info.room.PmsRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GuestService extends BaseService<Guest, Long> {
    private final GuestRepository guestRepository;


    @Transactional
    public Long save(GuestSaveRequestDto requestDto) {
        return guestRepository.save(requestDto.toEntity()).getId();

    }


    public List<GuestListResponseDto> findByL(String guestNm,String guestTel, String email) {
        BooleanBuilder builder = new BooleanBuilder();
        if(isNotEmpty(guestNm)){
            builder.and(qGuest.guestNm.eq(guestNm));
        }
        if(isNotEmpty(guestTel)){
            builder.and(qGuest.guestTel.eq(guestTel));
        }
        if(isNotEmpty(email)){
            builder.and(qGuest.email.eq(email));
        }
        List<Guest> entitis = select()
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return entitis.stream()
                .map(GuestListResponseDto::new)//
                .collect(Collectors.toList());
    }
}

