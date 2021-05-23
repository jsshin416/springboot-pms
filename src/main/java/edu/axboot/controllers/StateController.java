package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.controllers.dto.BookingSaveRequestDto;
import edu.axboot.controllers.dto.GuestResponseDto;
import edu.axboot.controllers.dto.PmsRoomListResponseDto;
import edu.axboot.controllers.dto.StateListResponseDto;
import edu.axboot.domain.pms.book.booking.BookingService;
import edu.axboot.domain.pms.book.state.StateService;
import edu.axboot.domain.pms.info.room.PmsRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/state")
public class StateController extends BaseController {

    @Inject
    private final StateService stateService;
    //Dto----------------------------------------------------------------------
    @RequestMapping( method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(@RequestParam(value = "rsvDt",required = false) String rsvDt){
        List<StateListResponseDto> list = stateService.findByL(rsvDt);
        return Responses.ListResponse.of(list);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public StateListResponseDto findById(@PathVariable Long id){
        return stateService.findById(id);
    }


}