package edu.axboot.domain.pms.book;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends AXBootJPAQueryDSLRepository<Booking, Long> {
}
