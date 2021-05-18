package edu.axboot.domain.pms.book.booking;

import edu.axboot.controllers.dto.BookingSaveRequestDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.controllers.dto.PmsRoomSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingService extends BaseService<Booking, Long> {
    private final BookingRepository bookingRepository;


   /* @Transactional
    public List<Long> save(List<BookingSaveRequestDto> dtos) {
        List<Long> ids = new ArrayList<Long>();
        for (BookingSaveRequestDto dto: dtos) {
            if (dto.is__created__()) {
                ids.add(bookingRepository.save(dto.toEntity()).getId());
            }
        }
        return ids;

    }*/
   @Transactional
   public Long save(BookingSaveRequestDto requestDto) {
       return bookingRepository.save(requestDto.toEntity()).getId();

   }

}