package edu.axboot.domain.pms.room;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsRoomRepository extends AXBootJPAQueryDSLRepository<PmsRoom, Long> {
}
