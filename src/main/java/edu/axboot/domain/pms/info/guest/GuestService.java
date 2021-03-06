package edu.axboot.domain.pms.info.guest;

import com.chequer.axboot.core.api.ApiException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.*;
import edu.axboot.fileupload.UploadFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            builder.and(qGuest.guestNm.like("%"+guestNm+"%"));
        }
        if(isNotEmpty(guestTel)){
            builder.and(qGuest.guestTel.like("%"+guestTel+"%"));
        }
        if(isNotEmpty(email)){
            builder.and(qGuest.email.like("%"+email+"%"));
        }
        List<Guest> entitis = select().select(
                Projections.fields(Guest.class, qGuest.id, qGuest.guestNm,
                        qGuest.guestNmEng, qGuest.guestTel,qGuest.email,
                        qGuest.gender,qGuest.brth, qGuest.langCd))
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return entitis.stream()
                .map(GuestListResponseDto::new)
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
            throw new IllegalArgumentException("?????? ????????? ????????? ????????????. id=" + updateDto.getId());
        }

        // JPA ????????? ???????????? ?????? (???????????? ?????? ???????????? ??????)
        guest.update(updateDto.getGuestTel(), updateDto.getEmail(), updateDto.getRmk());
        return guest.getId();
    }

    @Transactional(readOnly = true)
    public GuestResponseDto findById(Long id) {
        Guest guest = guestRepository.findOne(id);

        if (guest == null) {
            throw new IllegalArgumentException("?????? ????????? ????????? ????????????. id=" + id);
        }

        return new GuestResponseDto(guest);
    }

}

