package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.utils.MessageUtils;
import edu.axboot.controllers.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.pms.book.booking.BookingService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/booking")
public class BookingController extends BaseController {

    @Inject
    private final BookingService bookingService;

   /* @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody BookingSaveRequestDto request){
        bookingService.save(request);
        return ok();
    }*/
    @RequestMapping( method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list( @RequestParam(value = "filter",required = false) String filter,
                                        @RequestParam(value = "rsvNum",required = false) String rsvNum,
                                       @RequestParam(value = "roomTypCd",required = false) String roomTypCd,
                                       @RequestParam(value = "rsvStDt",required = false) String rsvStDt,
                                        @RequestParam(value = "rsvEndDt",required = false) String rsvEndDt,
                                        @RequestParam(value = "arrStDt",required = false) String arrStDt,
                                        @RequestParam(value = "arrEndDt",required = false) String arrEndDt,
                                        @RequestParam(value = "depStDt",required = false) String depStDt,
                                        @RequestParam(value = "depEndDt",required = false) String depEndDt,
                                        @RequestParam(value = "sttusCd",required = false) List<String> sttusCds){
        List<BookingListResponseDto> list = bookingService.findByL( filter,  rsvNum,  roomTypCd,  rsvStDt,  rsvEndDt,
                 arrStDt,  arrEndDt, depStDt,  depEndDt, sttusCds);
        return Responses.ListResponse.of(list);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public BookingResponseDto findById(@PathVariable Long id){
        return bookingService.findById(id);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public Responses.MapResponse save(@RequestBody BookingSaveRequestDto requestDto, HttpServletRequest request) {
        Long id = bookingService.save(requestDto);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("chkId", id);
        map.put("message", MessageUtils.getMessage(request, "ax.script.onsave"));
        return Responses.MapResponse.of(map);
    }


    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse update(@RequestBody List<BookingStateRequestDto> requestDtos) {
        bookingService.updateByStatus(requestDtos);
        return ok();
    }


}