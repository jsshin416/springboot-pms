package edu.axboot.domain.pms.book.memo;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class MemoService extends BaseService<Memo, Long> {
    private MemoRepository memoRepository;

    @Inject
    public MemoService(MemoRepository memoRepository) {
        super(memoRepository);
        this.memoRepository = memoRepository;
    }

    public List<Memo> gets(RequestParams<Memo> requestParams) {
        return findAll();
    }
}