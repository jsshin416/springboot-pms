package edu.axboot.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookingStateRequestDto {
    private Long id;
    private String sttusCd;

    @Builder
    public BookingStateRequestDto(Long id, String sttusCd){
        this.id = id;
        this.sttusCd =sttusCd;
    }
}
