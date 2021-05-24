package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.book.memo.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Getter
@NoArgsConstructor
public class MemoResponseDto {
    private Long id;
    private String memoCn;
    private String memoDate;



    public MemoResponseDto(Memo entity) {
        DateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

        this.id = entity.getId();
        this.memoCn = entity.getMemoCn();
        this.memoDate= format.format(entity.getMemoDtti());
    }


    }
