package edu.axboot.domain.pms.book.booking;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import edu.axboot.domain.pms.book.booking.Booking;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends AXBootJPAQueryDSLRepository<Booking, Long> {
}
