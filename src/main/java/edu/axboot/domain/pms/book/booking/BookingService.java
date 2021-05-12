package edu.axboot.domain.pms.book;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class BookingService extends BaseService<Booking, Long> {
    private BookingRepository bookingRepository;

    @Inject
    public BookingService(BookingRepository bookingRepository) {
        super(bookingRepository);
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> gets(RequestParams<Booking> requestParams) {
        return findAll();
    }
}