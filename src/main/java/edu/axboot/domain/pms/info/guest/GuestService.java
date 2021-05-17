package edu.axboot.domain.pms.info.guest;

import com.querydsl.core.BooleanBuilder;
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
    public List<Long> save(List<GuestSaveRequestDto> dtos) {
        List<Long> ids = new ArrayList<Long>();
        for (GuestSaveRequestDto dto: dtos) {
            if (dto.is__deleted__()) {
                guestRepository.delete(dto.getId());
                ids.add(dto.getId());
            } else {
                ids.add(guestRepository.save(dto.toEntity()).getId());
            }
        }
        return ids;
    }


}

