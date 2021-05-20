package edu.axboot.domain.pms.book.state;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import edu.axboot.domain.pms.book.booking.Booking;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends AXBootJPAQueryDSLRepository<Booking, Long> {
}
