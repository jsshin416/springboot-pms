package edu.axboot.domain.pms.guest;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsGuestRepository extends AXBootJPAQueryDSLRepository<PmsGuest, Long> {
}
