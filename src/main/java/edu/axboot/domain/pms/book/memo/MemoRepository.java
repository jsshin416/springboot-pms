package edu.axboot.domain.pms.book.memo;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends AXBootJPAQueryDSLRepository<Memo, Long> {
}
