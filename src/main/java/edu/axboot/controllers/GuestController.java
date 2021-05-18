package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.controllers.dto.GuestListResponseDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.controllers.dto.PmsRoomListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.pms.info.guest.GuestService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/guest")
public class GuestController extends BaseController {

    @Inject
    private final GuestService guestService;

    @RequestMapping( method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list( @RequestParam(value = "guestNm",required = false) String guestNm,
                                        @RequestParam(value = "guestTel", required = false) String guestTel,
                                       @RequestParam (value ="email",required = false) String email){
        List<GuestListResponseDto> list = guestService.findByL(guestNm,guestTel,email );
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody GuestSaveRequestDto request){
        guestService.save(request);
        return ok();
    }


}