package edu.axboot.domain.pms.book.state;

import edu.axboot.controllers.dto.BookingSaveRequestDto;
import edu.axboot.domain.BaseService;
import edu.axboot.domain.pms.book.booking.Booking;
import edu.axboot.domain.pms.book.booking.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StateService extends BaseService<Booking, Long> {
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

    /*@Transactional
    public long save(BookingSaveRequestDto saveDto) {
        long id = 0;
        String rsvDt = LocalDate.now().toString();
        Booking todayLastChk = select().select(
                Projections.fields(Booking.class, qBooking.sno))
                .from(qBooking)
                .where(qBooking.rsvDt.eq(rsvDt))
                .orderBy(qBooking.sno.desc())
                .fetchFirst();
        int sno = 1;
        if (todayLastChk != null) {
            sno = todayLastChk.getSno() + 1;
        }
        Booking booking = saveDto.toEntity();
        Booking.예약번호생성(rsvDt, sno);
//        id = bookingRepository.save(chk).getId();
        //TODO 투숙객 처리
        //TODO 투숙메모 처리
        return id;
    }*/

}