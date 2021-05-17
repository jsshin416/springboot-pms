package edu.axboot.domain.pms.info.guest;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class PmsGuestService extends BaseService<PmsGuest, Long> {
    private PmsGuestRepository pmsGuestRepository;

    @Inject
    public PmsGuestService(PmsGuestRepository pmsGuestRepository) {
        super(pmsGuestRepository);
        this.pmsGuestRepository = pmsGuestRepository;
    }

    public List<PmsGuest> gets(RequestParams<PmsGuest> requestParams) {
        return findAll();
    }
}