package edu.axboot.domain.pms.info.room;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsRoomRepository extends AXBootJPAQueryDSLRepository<PmsRoom, Long> {
}
