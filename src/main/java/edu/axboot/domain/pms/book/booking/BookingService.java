package edu.axboot.domain.pms.book.booking;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.pms.book.memo.Memo;
import edu.axboot.domain.pms.book.memo.MemoRepository;
import edu.axboot.domain.pms.info.guest.Guest;
import edu.axboot.domain.pms.info.guest.GuestRepository;
import edu.axboot.utils.SessionUtils;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.jpa.JPAExpressions.select;

@RequiredArgsConstructor
@Service
public class BookingService extends BaseService<Booking, Long> {
    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final MemoRepository memoRepository;


    @Transactional      //TODO 투숙객 처리
    public long save(BookingSaveRequestDto saveDto) {
        long id = 0;
        Guest guest = Guest.builder()
                .id(saveDto.getGuestId())
                .guestNm(saveDto.getGuestNm())
                .guestNmEng(saveDto.getGuestNmEng())
                .guestTel(saveDto.getGuestTel())
                .email(saveDto.getEmail())
                .brth(saveDto.getBrth())
                .gender(saveDto.getGender())
                .langCd(saveDto.getLangCd())
                .build();

        Long guestId = guestRepository.save(guest).getId();//투숙객 저장

        Booking booking =null;
        if(saveDto.getId() == null){
            booking = saveDto.toEntity();
            booking.투숙번호갱신(guestId);
            id = 신규예약저장(booking);
        }else{
            booking = bookingRepository.findOne(saveDto.getId());
            booking.투숙번호갱신(guestId);
            booking.예약수정(guestId,saveDto.getArrDt(),saveDto.getNightCnt(),saveDto.getDepDt(),
                    saveDto.getRoomTypCd(), saveDto.getAdultCnt(),saveDto.getChldCnt(),
                    saveDto.getGuestNm(),saveDto.getGuestNmEng(), saveDto.getGuestTel(),
                    saveDto.getEmail(),saveDto.getLangCd(),saveDto.getBrth(),saveDto.getGender(),
                    saveDto.getSaleTypCd(),saveDto.getSrcCd(),saveDto.getPayCd(),
                    saveDto.getAdvnYn(),saveDto.getSalePrc(), saveDto.getSvcPrc());
        id = saveDto.getId();
        }
        //투숙메모 처리
        this.saveMemo(booking.getRsvNum(), saveDto.getMemoList());
        return id;

    }

    private long 신규예약저장(Booking booking) {
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
        booking.예약번호생성(rsvDt, sno);
        return bookingRepository.save(booking).getId();
    }
    private void saveMemo(String rsvNum, List<MemoSaveRequestDto> memoDtos) {
        for(MemoSaveRequestDto memoDto: memoDtos){
            if(memoDto.is__created__()){
                Memo lastMemo = select().select(
                        Projections.fields(Memo.class, qMemo.sno))
                        .from(qMemo)
                        .where(qMemo.rsvNum.eq(rsvNum))
                        .orderBy(qMemo.sno.desc())
                        .fetchFirst();
                int snoMemo = 1;
                if (lastMemo != null) {
                    snoMemo = lastMemo.getSno() + 1;
                }
                Memo memo = Memo.builder()
                        .rsvNum(rsvNum)
                        .sno(snoMemo)
                        .memoCn(memoDto.getMemoCn())
                        .memoDtti(Timestamp.valueOf(LocalDateTime.now()))
                        .memoMan(SessionUtils.getCurrentLoginUserCd())
                        .delYn("N")
                        .build();
                 memoRepository.save(memo);
            }else if(memoDto.is__modified__()){
                Memo memo =memoRepository.findOne(memoDto.getId());
            }else if(memoDto.is__deleted__()){
                Memo memo = memoRepository.findOne(memoDto.getId());
                memo.delete();
            }
        }
    }
    @Transactional(readOnly = true)
    public List<BookingListResponseDto> findByL(String filter, String rsvNum, String roomTypCd, String rsvStDate, String rsvEndDate,
                                                String arrStDate, String arrEndDate,String depStDate, String depEndDate, List<String> sttusCds) {
        BooleanBuilder builder = new BooleanBuilder();
        if(isNotEmpty(filter)){
            BooleanBuilder builder2 = new BooleanBuilder();
            builder2.or(qBooking.guestNm.like("%"+filter+"%"));
            builder2.or(qBooking.guestTel.like("%"+filter+"%"));
            builder2.or(qBooking.email.like("%"+filter+"%"));
            builder.and(builder2);
        }
        if(isNotEmpty(rsvNum)){
            builder.and(qBooking.roomNum.like("%"+rsvNum+"%"));
        }
        if(isNotEmpty(roomTypCd)){
            builder.and(qBooking.roomTypCd.like("%"+roomTypCd+"%"));
        }
        if(isNotEmpty(rsvStDate)) {
            if(isNotEmpty(rsvEndDate)){
                builder.and(qBooking.rsvDt.between(rsvStDate,rsvEndDate));
            }else{
                builder.and(qBooking.rsvDt.goe(rsvStDate));
            }
        }
        if(isNotEmpty(arrStDate)) {
            if(isNotEmpty(arrEndDate)){
                builder.and(qBooking.arrDt.between(arrStDate,arrEndDate));
            }else{
                builder.and(qBooking.arrDt.goe(arrStDate));
            }

        }
        if(isNotEmpty(depStDate)) {
            if(isNotEmpty(depEndDate)){
                builder.and(qBooking.depDt.between(depStDate,depEndDate));
            }else{
                builder.and(qBooking.depDt.goe(depStDate));
            }
        }
        if(sttusCds.size() >0){
            BooleanBuilder builder2 = new BooleanBuilder();
            for(String sttusCd: sttusCds){
                builder2.or(qBooking.sttusCd.eq(sttusCd));
            }
            builder.and(builder2);
        }

        List<Booking> entitis = select().select(
                Projections.fields(Booking.class,
                        qBooking.id, qBooking.rsvNum, qBooking.rsvDt,qBooking.arrDt, qBooking.depDt, qBooking.nightCnt,
                        qBooking.roomTypCd, qBooking.roomNum, qBooking.saleTypCd, qBooking.srcCd, qBooking.sttusCd, qBooking.guestNm))
                .from(qBooking)
                .where(builder)
                .orderBy(qBooking.rsvNum.asc())
                .fetch();

        return entitis.stream()
                .map(BookingListResponseDto::new)//
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookingResponseDto findById(Long id) {
        Booking book = bookingRepository.findOne(id);

        if (book == null) {
            throw new IllegalArgumentException("해당 투숙객 정보가 없습니다. id=" + id);
        }
        return new BookingResponseDto(book);
    }

    @Transactional
    public int updateByStatus(List<BookingStateRequestDto> requestDtos){
     int cnt =0;
     for(BookingStateRequestDto dto: requestDtos){
         Booking booking = bookingRepository.findOne(dto.getId());
         booking.예약상태변경(dto.getSttusCd());
         cnt++;
     }
     return cnt;
    }


}