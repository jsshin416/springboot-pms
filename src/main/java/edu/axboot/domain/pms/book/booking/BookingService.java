package edu.axboot.domain.pms.book.booking;

import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.pms.book.memo.Memo;
import edu.axboot.domain.pms.book.memo.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;

@RequiredArgsConstructor
@Service
public class BookingService extends BaseService<Booking, Long> {
    private final BookingRepository bookingRepository;
    private final MemoRepository memoRepository;


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
//   @Transactional
//   public Long save(BookingSaveRequestDto requestDto) {
//       return bookingRepository.save(requestDto.toEntity()).getId();
//
//   }
   @Transactional
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
        Booking Booking = saveDto.toEntity();
        Booking.예약번호생성(rsvDt, sno);
        id = bookingRepository.save(Booking).getId();
        //TODO 투숙객 처리

        //TODO 투숙메모 처리
        return id;
    }



}