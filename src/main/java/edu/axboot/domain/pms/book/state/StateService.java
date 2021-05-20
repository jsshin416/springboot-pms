package edu.axboot.domain.pms.book.state;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.BookingSaveRequestDto;
import edu.axboot.controllers.dto.PmsRoomListResponseDto;
import edu.axboot.controllers.dto.StateListResponseDto;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.pms.book.booking.Booking;
import edu.axboot.domain.pms.book.booking.BookingRepository;
import edu.axboot.domain.pms.info.room.PmsRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StateService extends BaseService<Booking, Long> {
    //private final BookingRepository bookingRepository;
    private final StateRepository stateRepository;




    public List<StateListResponseDto> findByL(String rsvDt) {
        BooleanBuilder builder = new BooleanBuilder();
        if(isNotEmpty(rsvDt)){
            builder.and(qBooking.rsvDt.eq(rsvDt));
        }
        List<Booking> entitis = select()
                .from(qBooking)
                .where(builder)
                .orderBy(qBooking.rsvDt.asc())
                .fetch();

        return entitis.stream()
                .map(StateListResponseDto::new)//
                .collect(Collectors.toList());
    }



}