package edu.axboot.domain.pms.info.guest;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;


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


    @Transactional(readOnly = true)
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

    @Transactional
    public long update(GuestSaveRequestDto saveDto) {
        return guestRepository.save(saveDto.toEntity()).getId();
    }

    @Transactional
    public Long update(GuestUpdateRequestDto updateDto) {
        Guest guest = guestRepository.findOne(updateDto.getId());

        if (guest == null) {
            throw new IllegalArgumentException("해당 투숙객 정보가 없습니다. id=" + updateDto.getId());
        }

        // JPA 영속성 컨텍스트 사용 (엔티티를 영구 저장하는 환경)
        guest.update(updateDto.getGuestTel(), updateDto.getEmail(), updateDto.getRmk());
        return guest.getId();
    }

    @Transactional(readOnly = true)
    public GuestResponseDto findById(Long id) {
        Guest guest = guestRepository.findOne(id);

        if (guest == null) {
            throw new IllegalArgumentException("해당 투숙객 정보가 없습니다. id=" + id);
        }

        return new GuestResponseDto(guest);
    }


}

