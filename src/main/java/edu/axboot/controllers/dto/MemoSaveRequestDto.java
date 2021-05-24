package edu.axboot.controllers.dto;

import edu.axboot.domain.pms.book.memo.Memo;
import lombok.Builder;
import lombok.Getter;


@Getter
public class MemoSaveRequestDto {
    private Long id;
    private String memoCn;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public MemoSaveRequestDto(Long id, String memoCn,
                               boolean __created__, boolean __modified__, boolean __deleted__){
        this.id =id;
        this.memoCn =memoCn;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;

    }

    public Memo toEntity() {
        return Memo.builder()
                .id(id)
                .memoCn(memoCn)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
