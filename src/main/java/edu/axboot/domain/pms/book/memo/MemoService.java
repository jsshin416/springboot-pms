package edu.axboot.domain.pms.book.memo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService extends BaseService<Memo, Long> {
    private final MemoRepository memoRepository;



}