package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.book.booking.Booking;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookingStateRequestDto  {
    private Long id;
    private String sttusCd;

    @Builder
    public BookingStateRequestDto(Long id, String sttusCd){
        this.id = id;
        this.sttusCd =sttusCd;
    }

   /* public Booking toEntity() {
        return Booking.builder()
                .sttusCd(sttusCd)
                .build();
    }*/
}
